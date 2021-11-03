package com.example.place

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class VoiceManager(context: Context, private val locale: Locale): TextToSpeech.OnInitListener{

    private lateinit var listener: VoiceManagerListener

    interface VoiceManagerListener{
        fun onLaunchSuccess(result: Boolean)
    }

    fun setLaunchSuccessListener(listener: VoiceManagerListener){
        this.listener = listener
    }


    private var textToSpeech = TextToSpeech(context, this)
    var isVoiceMode = true

    override fun onInit(status: Int){
        if(!isVoiceMode)return
        if (status == TextToSpeech.SUCCESS) {
            if (textToSpeech.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE){
                textToSpeech.language = locale
                listener.onLaunchSuccess(true)
            } else {
                Log.i(TAG, "言語の設定するのに失敗しました．システムの音声出力言語設定を確認してください．")
                listener.onLaunchSuccess(false)
            }
        } else {
            // Tts init 失敗
            Log.i(TAG, "ttsの初期化に失敗しました．音声再生を中止します．")
            listener.onLaunchSuccess(false)
        }
    }

    fun speak(text : String, utteranceId: String){
        if(!isVoiceMode)return
        textToSpeech.speak(text,TextToSpeech.QUEUE_ADD, null, utteranceId)
    }

    fun shutdown(){
        if(!isVoiceMode)return
        textToSpeech.shutdown()
    }

    fun isSpeaking():Boolean{
        return textToSpeech.isSpeaking
    }



    companion object{
        private const val TAG = "VoiceManager"
    }

}



