package com.example.place

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText


/*
データ計測時のメタデータやラベルデータなどを入力する用
 */
class InputNoteActivity : AppCompatActivity() {

    //メタデータを入れる用
    private var metaData = MetaData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_note)

        var etLabel = findViewById<EditText>(R.id.etLabel)
        var etMeta = findViewById<EditText>(R.id.etMeta)
        var setLabelBtn = findViewById<Button>(R.id.setLabelBtn)

        if(metaData.labelData != null){
            etLabel.setText(metaData.labelData)
        }
        if(metaData.otherData != null){
            etMeta.setText(metaData.otherData)
        }

        setLabelBtn.setOnClickListener{
            Log.d("aa", etLabel.text.toString() + etMeta.text.toString())
            metaData.labelData = etLabel.text.toString()
            metaData.otherData = etMeta.text.toString()

            val mainIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainIntent)
        }


    }
}