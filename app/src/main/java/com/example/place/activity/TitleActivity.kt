package com.example.place.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.place.R

class TitleActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {

        // MainActivityへ遷移させる
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        // ここでfinish()を呼ばないと遷移後のActivityでAndroidの戻るボタンを押すとスプラッシュ画面に戻ってしまう
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        // スプラッシュ表示Nms(N秒)後にrunnableを呼んでTitleActivityへ遷移させる
        handler.postDelayed(runnable, 1000)
    }

    override fun onBackPressed() {
        //無記入で何もしないようにする
    }

    override fun onStop() {
        super.onStop()

        // スプラッシュ画面中にアプリを落とした時にはrunnableが呼ばれないようにする
        // これがないとアプリを消した後にまた表示される
        handler.removeCallbacks(runnable)
    }
}