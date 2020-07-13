package com.example.place

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText


/*
データ計測時のメタデータやラベルデータなどを入力する用
 */
class InputNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_note)

        var etLabel = findViewById<EditText>(R.id.etLabel)
        var etMeta = findViewById<EditText>(R.id.etMeta)
        var setLabelBtn = findViewById<Button>(R.id.setLabelBtn)

        setLabelBtn.setOnClickListener{
            Log.d("aa", etLabel.text.toString() + etMeta.text.toString())
        }


    }
}