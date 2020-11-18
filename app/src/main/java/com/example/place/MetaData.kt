package com.example.place

import android.app.Application

//ラベルデータやプログラムを保持する用のデータクラス
class MetaData : Application() {
    var labelData: String? = null
    var otherData: String? = null
    var sensingFilePath : String? = null
    var quizPattern : String ? = null

    //
    var dataList : MutableList<HashMap<String, Any?>> = arrayListOf()


    //グローバルに変数を保存する用
    companion object {
        private var instance : MetaData? = null

        @JvmStatic
        fun  getInstance(): MetaData {
            if (instance == null)
                instance = MetaData()

            return instance!!
        }
    }


}