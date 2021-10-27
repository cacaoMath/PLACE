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
    private var quizSet = Quiz().GetQuizSet(30, getInstance().quizPattern)
    private lateinit var cardStackView: CardStackView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flashCardBinding = ActivityFlashCardBinding.inflate(layoutInflater)
        setContentView(flashCardBinding.root)

        cardStackView = flashCardBinding.cardStackView

        cardStackView.layoutManager = CardStackLayoutManager(this,this)

        cardStackView.adapter = MyAdapter(quizSet)
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d(TAG,"Disappeared ${position} ${quizSet.size}")

        if(position == quizSet.size -1){
            // 結果画面へ移動
//            val resultIntent = Intent(applicationContext, ResultActivity::class.java)
//            startActivity(resultIntent)
            finish()
        }

    }

    companion object{
        private const val TAG = "FlashCardActivity"
    }
}