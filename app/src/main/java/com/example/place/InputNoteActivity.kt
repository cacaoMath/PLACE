package com.example.place

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.SpinnerAdapter


/*
データ計測時のメタデータやラベルデータなどを入力する用
 */
class InputNoteActivity : AppCompatActivity() {

    //メタデータを入れる用
    private var metaData = MetaData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_note)

        var labelSpn = findViewById<Spinner>(R.id.labelSpn)
        var etMeta = findViewById<EditText>(R.id.etMeta)
        var setLabelBtn = findViewById<Button>(R.id.setLabelBtn)
        var qstSpn = findViewById<Spinner>(R.id.qstSpn)

        if(metaData.labelData != null){
            val strArray = resources.getStringArray(R.array.labelList)
            var index = 0
            for (item in strArray){
                if(item == metaData.labelData){
                    qstSpn.setSelection(index)
                }
                index++
            }
        }
        if(metaData.otherData != null){
            etMeta.setText(metaData.otherData)
        }

        if(metaData.quizPattern != null){
            val strArray = resources.getStringArray(R.array.patternList)
            var index = 0
            for (item in strArray){
                if(item == metaData.quizPattern){
                    qstSpn.setSelection(index)
                }
                index++
            }
        }

        setLabelBtn.setOnClickListener{
            metaData.labelData = labelSpn.selectedItem as String
            metaData.otherData = etMeta.text.toString()
            metaData.quizPattern = qstSpn.selectedItem as String
            val mainIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainIntent)
        }


    }
}