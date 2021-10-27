package com.example.place.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.place.MetaData.Companion.getInstance
import com.example.place.MyAdapter
import com.example.place.Quiz
import com.example.place.R
import com.example.place.databinding.ActivityFlashCardBinding
import com.yuyakaido.android.cardstackview.*
import java.util.*

class FlashCardActivity : AppCompatActivity(), CardStackListener{
    private lateinit var flashCardBinding: ActivityFlashCardBinding
    private lateinit var quizSet : Array<Array<String>>

    private lateinit var cardStackView: CardStackView
    private val questionNum = 30
    private var learnedWordNumList = mutableListOf<Int>()
    private var learningTimeList = mutableListOf<Long>()
    private var rememberingOrNotList = mutableListOf<Int>()

    private var isRemembering = false
    private var wordAppearedTime = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flashCardBinding = ActivityFlashCardBinding.inflate(layoutInflater)
        setContentView(flashCardBinding.root)
        quizSet = Quiz().GetQuizSet(questionNum, getInstance().quizPattern)


        cardStackView = flashCardBinding.cardStackView

        cardStackView.layoutManager = CardStackLayoutManager(this,this)

        cardStackView.adapter = MyAdapter(quizSet)

        //todo: onBackPressの時の処理などや，10分計測のアラームの追加
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