package com.example.place.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AlarmManagerCompat
import com.example.place.MetaData.Companion.getInstance
import com.example.place.R
import com.example.place.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding
    private var alertDialog: AlertDialog? = null
    var metaData = getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myToolbar = binding.toolBar
        setSupportActionBar(myToolbar)
        val mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser
        val currentUserTxt = binding.currentUserTxt
        currentUserTxt.text = "${currentUser!!.email}\nでログインしています．"

        val startBtn = binding.startBtn
        val metaBtn = binding.metaBtn


        startBtn.setOnClickListener { //問題画面へ遷移
            //メタデータを選択しないと遷移しないようにする
            if (metaData.quizPattern != null || metaData.labelData != null) {
                startMeasurementAlarm()
                val qIntent = Intent(applicationContext, QuestionActivity::class.java)
                startActivity(qIntent)
            } else {
                val toast = Toast.makeText(applicationContext, "メタデータを入力して下さい.", Toast.LENGTH_LONG)
                // 位置調整
                toast.show()
            }
            Log.d(TAG, "onClick:start_btn")
        }

        metaBtn.setOnClickListener { //メタデータ入力画面へ遷移
            val inputMetaIntent = Intent(applicationContext, InputNoteActivity::class.java)
            startActivity(inputMetaIntent)
            Log.d(TAG, "onClick:meta_btn")
        }
    }

    //10分計測のためのアラームをセットする
    private fun startMeasurementAlarm() {

        // 時間をセットする
        val calendar = Calendar.getInstance()
        // Calendarを使って現在の時間をミリ秒で取得
        calendar.timeInMillis = System.currentTimeMillis()
        // metadataに保存されている分数後に設定
        calendar.add(Calendar.SECOND, metaData.measurementTime * 60)

        //時間精度デバック用
        val df: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS")
        val date = Date(System.currentTimeMillis())
        Log.d("alarmCheck_start", df.format(date))

        //暗黙的なBroadCast
        val intent = Intent("STOP")
        val pending = PendingIntent.getBroadcast(
            applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        )

        // アラームをセットする
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        //                        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
        AlarmManagerCompat.setExact(am, AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pending)
        Toast.makeText(
            applicationContext,
            "${metaData.measurementTime}分の計測を始めます.", Toast.LENGTH_SHORT
        ).show()
    }

    private fun cancelMeasurementAlarm() {
        Toast.makeText(applicationContext, "中断しました", Toast.LENGTH_SHORT).show()
        // アラームの削除
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent("STOP")
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.config) { //設定画面へ遷移
            val configIntent = Intent(applicationContext, ConfigActivity::class.java)
            startActivity(configIntent)
            Log.d(TAG, "onClick:config_btn")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //戻るでアプリ終了
    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this@MainActivity)
            .setCancelable(false)
            .setTitle("確認")
            .setMessage("終了してよろしいですか")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No") { _, _ -> alertDialog!!.dismiss() }.show()
    }

}