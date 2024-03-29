package com.example.place.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.place.databinding.ActivitySignupBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    // Initialize Firebase Auth
    private var auth : FirebaseAuth = Firebase.auth
    private lateinit var binding: ActivitySignupBinding
    private lateinit var envSpn : Spinner
    private lateinit var mlSpn : Spinner
    private lateinit var createAccountBtn : Button
    private  lateinit var loadingBar : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        envSpn = binding.envSpn
        mlSpn = binding.mlSpn
        createAccountBtn = binding.createAccountBtn
        loadingBar = binding.loadingBar

        loadingBar.visibility = ProgressBar.INVISIBLE

        createAccountBtn.setOnClickListener {
            if(binding.etSignupPass.text.toString().length > 6){
                loadingBar.visibility = ProgressBar.VISIBLE
                signUp(binding.etSignupEmail.text.toString(), binding.etSignupPass.text.toString())
                createAccountBtn.isEnabled = false
            }else{
//                Toast.makeText(baseContext, "パスワードは7文字以上にしてください",
//                        Toast.LENGTH_SHORT).show()
                Snackbar.make(binding.root,"パスワードは7文字以上にしてください", Snackbar.LENGTH_SHORT).show()
            }

        }
    }


    private fun signUpMsg(user : FirebaseUser?){
        if(user != null){
            Log.d(TAG,"sign up : this user is exist")
            Toast.makeText(baseContext, "サインアップ完了しました",
                    Toast.LENGTH_SHORT).show()
        }else{
            Log.d(TAG,"sign up : Error")

        }

    }


    private fun signUp(email :String, password : String){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val db = FirebaseFirestore.getInstance()
                        val user = auth.currentUser

                        user?.uid?.let {
                            db.collection("users").document(it).collection("data")
                                .add(
                                    hashMapOf(
                                        "mLearningExp" to mlSpn.selectedItem,
                                        "mLearningEnvironment" to envSpn.selectedItem,
                                        "e-mail" to user.email
                                    )
                                )
                                .addOnSuccessListener {
                                    Log.d(TAG, "DocumentSnapshot added with ID: ")
                                    signUpMsg(user)

                                    //サインアップ完了でメール送信
                                    user.sendEmailVerification()
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Log.d(TAG, "Email sent.")
                                            }
                                        }
                                    // 登録後ホームへ移行
                                    gotoMain()
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                        }


                    } else {
                        // If sign up fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(baseContext, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show()
                        Snackbar.make(binding.root,"Authentication failed.", Snackbar.LENGTH_SHORT).show()

                        loadingBar.visibility = ProgressBar.INVISIBLE
                        signUpMsg(null)
                        createAccountBtn.isEnabled = true

                    }

                    // ...
                }
    }

    private fun gotoMain(){
        //ホーム画面へ遷移
        val mainIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    companion object{
        private const val TAG ="SignupActivity"
    }
}