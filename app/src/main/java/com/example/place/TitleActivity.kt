package com.example.place

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class TitleActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private lateinit var auth: FirebaseAuth

    var userEmail: String? = null
    var userPassword: String? = null
    private var tvLog: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        val etUserEmail = findViewById<EditText>(R.id.etUserEmail)
        val etUserPassword = findViewById<EditText>(R.id.etUserPassword)
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        val signInBtn = findViewById<Button>(R.id.signInBtn)
        tvLog = findViewById<TextView>(R.id.tvLog)


        // Initialize Firebase Auth
        auth = Firebase.auth


        signInBtn.setOnClickListener{
            if(!TextUtils.isEmpty(etUserEmail.text.toString()) && !TextUtils.isEmpty(etUserPassword.text.toString()) ){
                signIn(etUserEmail.text.toString(), etUserPassword.text.toString())
            }

        }

        signUpBtn.setOnClickListener{
            if(!TextUtils.isEmpty(etUserEmail.text.toString()) && !TextUtils.isEmpty(etUserPassword.text.toString())){
                signUp(etUserEmail.text.toString(), etUserPassword.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        //ログインしているとホームへ移行
        if(currentUser != null){
            Log.d(TAG,"This account is OK. Auto Signed In.")
            //ホーム画面へ遷移
            val mainIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainIntent)
        }
        else{
            tvLog?.text = "Please Sign Up or Sign In"
        }
    }


    fun signUpMsg(user : FirebaseUser?){
        if(user != null){
            Log.d(TAG,"sign up : this user is exist")
            tvLog?.text = "Made your account"
        }else{
            Log.d(TAG,"sign up : Error")
            tvLog?.text = "Error"
        }

    }

    fun signInMsg(user : FirebaseUser?){
        if(user != null){
            Log.d(TAG,"sign In : this user is exist")
            tvLog?.text = "Sign In success"
        }else{
            Log.d(TAG,"sign In : There is NO account your e-mail")
            tvLog?.text = "NO account your e-mail. Please Sign Up."
        }

    }

    fun signUp(email :String, password : String){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        signUpMsg(user)

                        //サインアップ完了でメール送信
                        user?.sendEmailVerification()
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "Email sent.")
                                    }
                                }
                        // 登録後ホームへ移行
                        val mainIntent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(mainIntent)
                    } else {
                        // If sign up fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        signUpMsg(null)
                    }

                    // ...
                }
    }

    fun signIn(email :String, password : String){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        signInMsg(user)
                        Log.d(TAG, "password " + password)
                        //ホーム画面へ遷移
                        val mainIntent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(mainIntent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Log.d(TAG, "password " + password)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        signInMsg(null)
                        // ...
                    }

                    // ...
                }
    }

    fun getUid(){
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
    }

}