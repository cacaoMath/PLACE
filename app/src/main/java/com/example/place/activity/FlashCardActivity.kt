package com.example.place.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.preference.PreferenceManager
import com.example.place.*
import com.example.place.MetaData.Companion.getInstance
import com.example.place.databinding.ActivityFlashCardBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class FlashCardActivity : ScopedAppActivity(), CardStackListener{
    private lateinit var flashCardBinding: ActivityFlashCardBinding
    private lateinit var quizSet : Array<Array<String>>

    private lateinit var cardStackView: CardStackView
    private val questionNum = 30
    private var learnedWordNumList = mutableListOf<Int>()
    private var learningTimeList = mutableListOf<Long>()
    private var rememberingOrNotList = mutableListOf<Int>()

    private var isRemembering = false
    private var wordAppearedTime = 0L

    private val dt = DataTransferKt()

    private val myReceiver = object: MeasurementABReceiver(this) {
        override fun onReceive(context: Context?, intent: Intent?) {
            super.onReceive(context, intent)

            //終了時データを転送する
            dt.addFlashCardResultData(
                learnedWordNumList.toIntArray(),
                learningTimeList.toLongArray(),
                rememberingOrNotList.toIntArray()
            )
            dt.sendResultData()
            Log.d(TAG, "measurement finish")
        }
    }

    private var voiceManagerEn: TextToSpeech? = null
    private var voiceManagerJp: TextToSpeech? = null

    private lateinit var cameraTask: CameraTask

    private var isVoiceMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flashCardBinding = ActivityFlashCardBinding.inflate(layoutInflater)
        setContentView(flashCardBinding.root)
        quizSet = Quiz().GetQuizSet(questionNum, getInstance().quizPattern)


        cardStackView = flashCardBinding.cardStackView

        cardStackView.layoutManager = CardStackLayoutManager(this,this).apply {
            setOverlayInterpolator(LinearInterpolator())
            setVisibleCount(1)
        }


        cardStackView.adapter = MyAdapter(quizSet)


        cameraTask = CameraTask(this,flashCardBinding.cameraPreview)


        isVoiceMode = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("voiceMode", false)
        if(isVoiceMode){
            launch {
                launchAdditionalOption(baseContext)
            }
        }

        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("seeThrow", false)){
            cameraTask.launchSeeThrow()
        }else{
            flashCardBinding.cameraPreview.isVisible = false
        }
    }

    private suspend fun launchAdditionalOption(context: Context){
        val launchTask = async(Dispatchers.IO) {
            var isTTSEnReady = false
            var isTTSJpReady = false
            voiceManagerEn = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    if (voiceManagerEn?.isLanguageAvailable(Locale.ENGLISH)!! >= TextToSpeech.LANG_AVAILABLE) {
                        voiceManagerEn?.language = Locale.ENGLISH
                        isTTSEnReady = true
                    } else {
                        Log.i(TAG, "英語の設定するのに失敗しました．システムの音声出力言語設定を確認してください．")
                        isTTSEnReady = false
                    }
                } else {
                    // Tts init 失敗
                    Log.i(TAG, "ttsの初期化に失敗しました．音声再生を中止します．")
                    isTTSEnReady = false
                }

            }
            voiceManagerJp = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    if (voiceManagerJp?.isLanguageAvailable(Locale.JAPANESE)!! >= TextToSpeech.LANG_AVAILABLE) {
                        voiceManagerJp?.language = Locale.JAPANESE
                        isTTSJpReady = true
                    } else {
                        Log.i(TAG, "日本語の設定するのに失敗しました．システムの音声出力言語設定を確認してください．")
                        isTTSJpReady = false
                    }
                } else {
                    // Tts init 失敗
                    Log.i(TAG, "ttsの初期化に失敗しました．音声再生を中止します．")
                    isTTSJpReady = false
                } }



            Log.i(TAG, "is TTS Ready ${isTTSEnReady && isTTSJpReady}")
            isTTSEnReady && isTTSJpReady
        }

        withContext(Dispatchers.Main){
            if(launchTask.await()){
                window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }

        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(myReceiver, IntentFilter("STOP"))
    }

    override fun onPause() {
        super.onPause()
        // ブロードキャストレシーバーを解除する
        unregisterReceiver(myReceiver)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("確認")
        builder.setMessage("計測が中止されます．\nよろしいですか？")
            .setPositiveButton(
                "いいえ"
            ) { _, _ -> }
            .setNegativeButton(
                "はい"
            ) { _, _ -> //10分計測のキャンセル処理
                myReceiver.cancelABReceiver()
                finish()
            }.show()
    }

    override fun onDestroy() {
        voiceManagerEn?.shutdown()
        voiceManagerJp?.shutdown()
        cameraTask.shutdown()
        super.onDestroy()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        when(direction){
            Direction.Left->isRemembering=true
            Direction.Right->isRemembering=false
            else -> Log.d(TAG, "Action is nothing")
        }
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
        wordAppearedTime = System.currentTimeMillis()
        val utteranceId = position.toString()

        if(isVoiceMode){
            voiceManagerEn?.speak(quizSet[position][1], TextToSpeech.QUEUE_ADD, null, utteranceId)
            voiceManagerJp?.speak(quizSet[position][6], TextToSpeech.QUEUE_ADD, null, utteranceId)
        }

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d(TAG,"Disappeared $position")

        //覚えていたら1，覚えていなければ0
        if(isRemembering){
            rememberingOrNotList.add(1)
        }else{
            rememberingOrNotList.add(0)
        }
        Log.d(TAG,rememberingOrNotList.toString())

        //スワイプした英単語に割り当てられている問題番号を追加
        learnedWordNumList.add(quizSet[position][7].toInt())
        Log.d(TAG,learnedWordNumList.toString())
        //英単語を見ていた時間を追加
        learningTimeList.add(System.currentTimeMillis()-wordAppearedTime)
        Log.d(TAG,learningTimeList.toString())

        Log.d(TAG,quizSet[position][7])

        if(position == quizSet.size -1){
            // 結果画面へ移動
            val resultIntent = Intent(applicationContext, ResultActivity::class.java)


            resultIntent.putExtra("LearnedWordNumList", learnedWordNumList.toIntArray())
            resultIntent.putExtra("RememberingOrNotList", learningTimeList.toLongArray())
            resultIntent.putExtra("LearningTimeList", rememberingOrNotList.toIntArray())
            startActivity(resultIntent)

            finish()
        }

    }

    companion object{
        private const val TAG = "FlashCardActivity"
    }



}