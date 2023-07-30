package com.example.place.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.place.EmailValidateStatus
import com.example.place.PasswordValidateStatus
import com.example.place.databinding.ActivityLoginBinding
import com.example.place.validateEmail
import com.example.place.validatePassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    // Initialize Firebase Auth
    private var auth: FirebaseAuth = Firebase.auth

    private lateinit var progressBar: ProgressBar
    private lateinit var tvLog: TextView
    private lateinit var signInBtn: Button
    private lateinit var signUpBtn: Button
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val etUserEmail = binding.etUserEmail
        val etUserPassword = binding.etUserPassword
        signUpBtn = binding.signUpBtn
        signInBtn = binding.signInBtn
        progressBar = binding.progressBar
        tvLog = binding.tvLog



        progressBar.visibility = ProgressBar.INVISIBLE

        signInBtn.setOnClickListener {

            val validateEmailResult = validateEmail(etUserEmail.text.toString())
            val validatePasswordResult = validatePassword(etUserPassword.text.toString())

            when {
                validateEmailResult == EmailValidateStatus.EMPTY || validatePasswordResult == PasswordValidateStatus.EMPTY -> {
                    Toast.makeText(
                        baseContext, "入力されていない項目があります", Toast.LENGTH_SHORT
                    ).show()
                }
                validateEmailResult == EmailValidateStatus.BAD_ADDRESS -> {
                    Toast.makeText(
                        baseContext, "有効なメールアドレスではありません", Toast.LENGTH_SHORT
                    ).show()
                }
                validatePasswordResult == PasswordValidateStatus.TOO_SHORT -> {
                    Toast.makeText(
                        baseContext, "パスワードは7文字以上にしてください", Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    signIn(etUserEmail.text.toString(), etUserPassword.text.toString())
                    signInBtn.isEnabled = false
                    signUpBtn.isEnabled = false
                }
            }
        }

        signUpBtn.setOnClickListener {
            val signupIntent = Intent(applicationContext, SignupActivity::class.java)
            startActivity(signupIntent)
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        //ログインしているとホームへ移行
        if (currentUser != null) {
            Log.d(TAG, "This account is OK. Auto Signed In.")
            //ホーム画面へ遷移
            val mainIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        } else {
            tvLog.text = "Please Sign Up or Sign In"
        }
    }

    override fun onBackPressed() {
        //無記入で何もしないようにする
    }


    private fun signInMsg(user: FirebaseUser?) {
        progressBar.visibility = ProgressBar.INVISIBLE
        if (user != null) {
            Log.d(TAG, "sign In : this user is exist")
            tvLog.text = "Sign In success"
        } else {
            Log.d(TAG, "sign In : There is NO account your e-mail")
            tvLog.text = "NO account your e-mail. Please Sign Up."
        }

    }


    private fun signIn(email: String, password: String) {
        tvLog.text = "Now Loading. Please wait ..."
        progressBar.visibility = ProgressBar.VISIBLE
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signIn:success")
                val user = auth.currentUser
                signInMsg(user)
                //Log.d(TAG, "password " + password)

                gotoMain()
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signIn:failure", task.exception)
                Log.d(TAG, "password $password")
                Toast.makeText(
                    baseContext, "Authentication failed.", Toast.LENGTH_SHORT
                ).show()
                signInMsg(null)
                signInBtn.isEnabled = true
                signUpBtn.isEnabled = true
                // ...
            }

            // ...
        }
    }

    private fun gotoMain() {
        //ホーム画面へ遷移
        val mainIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }


    companion object {
        private const val TAG = "LoginActivity"
    }

}