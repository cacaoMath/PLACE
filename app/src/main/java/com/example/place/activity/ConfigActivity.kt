package com.example.place.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.place.MetaData.Companion.getInstance
import com.example.place.databinding.ActivityConfigBinding
import com.google.firebase.auth.FirebaseAuth

open class ConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding
    private lateinit var configSaveBtn: Button
    private lateinit var signOutBtn: Button
    private lateinit var measurementTimeEt: EditText
    private val metaData = getInstance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)


        configSaveBtn = binding.configSaveBtn
        signOutBtn = binding.signoutBtn
        measurementTimeEt = binding.measurementTimeEt
        measurementTimeEt.setText(metaData.measurementTime.toString())
        configSaveBtn.setOnClickListener(View.OnClickListener {
            val inputTxt = measurementTimeEt.text.toString()
            when {
                inputTxt.contains(":") -> {
                    Toast.makeText(
                        applicationContext, "計測時間は数値だけを入れてください",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.i(TAG,"計測時間は数値だけを入れてください")
                }
                TextUtils.isEmpty(inputTxt) -> {
                    metaData.measurementTime = 10
                }
                else -> {
                    metaData.measurementTime = inputTxt.toInt()
                }
            }
            Log.d(TAG, "Saved!!")
            finish()
        })
        signOutBtn.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val titleIntent = Intent(applicationContext, TitleActivity::class.java)
            startActivity(titleIntent)
            finish()
        })
    }

    companion object {
        protected val TAG = ConfigActivity::class.java.simpleName
    }
}