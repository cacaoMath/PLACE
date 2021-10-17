package com.example.place.activity

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.place.MetaData
import com.example.place.R
import com.example.place.databinding.ActivityInputNoteBinding


/*
データ計測時のメタデータやラベルデータなどを入力する用
 */
class InputNoteActivity : AppCompatActivity() {
    private val TAG = InputNoteActivity::class.java.simpleName
    //メタデータを入れる用
    private var metaData = MetaData.getInstance()

    private lateinit var binding: ActivityInputNoteBinding
    private lateinit var labelSpn:Spinner
    private lateinit var etMeta:EditText
    private lateinit var qstSpn:Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        labelSpn = binding.labelSpn
        etMeta = binding.etMeta
        val setLabelBtn = binding.setLabelBtn
        qstSpn = binding.qstSpn

        if(metaData.labelData != null){
            val strArray = resources.getStringArray(R.array.labelList)
            for ((index, item) in strArray.withIndex()){
                if(item == metaData.labelData){
                    labelSpn.setSelection(index)
                }
            }
        }
        if(metaData.otherData != null){
            etMeta.setText(metaData.otherData)
            Log.i(TAG,"他の項目が入力されました．")
        }

        if(metaData.quizPattern != null){
            val strArray = resources.getStringArray(R.array.patternList)
            for ((index, item) in strArray.withIndex()){
                if(item == metaData.quizPattern){
                    qstSpn.setSelection(index)
                    Log.i(TAG,"問題パターンは${index}です．")
                }
            }
        }

        setLabelBtn.setOnClickListener{
            metaData.labelData = labelSpn.selectedItem.toString()
            metaData.otherData = etMeta.text.toString()
            metaData.quizPattern = qstSpn.selectedItem.toString()
            finish()
        }

    }

    //戻るでも同じ挙動にする
    override fun onBackPressed() {
        metaData.labelData = labelSpn.selectedItem.toString()
        metaData.otherData = etMeta.text.toString()
        metaData.quizPattern = qstSpn.selectedItem.toString()

        finish()
    }
}