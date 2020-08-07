package com.example.place

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        val startBtn = findViewById<Button>(R.id.startBtn)

        startBtn.setOnClickListener{
            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}