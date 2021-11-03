package com.example.place.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.place.*
import com.example.place.MetaData.Companion.getInstance
import com.example.place.databinding.ActivityFlashCardBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import java.util.*

class FlashCardActivity : AppCompatActivity(), CardStackListener, VoiceManager.VoiceManagerListener{
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

    private lateinit var voiceManagerEn: VoiceManager
    private lateinit var voiceManagerJp: VoiceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flashCardBinding = ActivityFlashCardBinding.inflate(layoutInflater)
        setContentView(flashCardBinding.root)
        quizSet = Quiz().GetQuizSet(questionNum, getInstance().quizPattern)


        cardStackView = flashCardBinding.cardStackView

        cardStackView.layoutManager = CardStackLayoutManager(this,this)

        cardStackView.adapter = MyAdapter(quizSet)

        voiceManagerEn = VoiceManager(this, Locale.ENGLISH)
        voiceManagerEn.setLaunchSuccessListener(this)
        voiceManagerJp = VoiceManager(this, Locale.JAPANESE)
        voiceManagerJp.setLaunchSuccessListener(this)

        if(!PreferenceManager.getDefaultSharedPreferences(this).getBoolean("voiceMode", false)){
            voiceManagerJp.isVoiceMode = false
            voiceManagerEn.isVoiceMode = false
        }else{
            //todo:音声再生の起動が終わるまで動かさないようにする．
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
        voiceManagerEn.shutdown()
        voiceManagerJp.shutdown()
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
        voiceManagerEn.speak(quizSet[position][1], utteranceId)
        voiceManagerJp.speak(quizSet[position][6], utteranceId)
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

    override fun onLaunchSuccess(result: Boolean) {
        if(result){
            Log.i(TAG,"音声再生機能が使えます．")

        }else{
            Log.i(TAG,"音声再生機能が使えません．")
        }
    }


}