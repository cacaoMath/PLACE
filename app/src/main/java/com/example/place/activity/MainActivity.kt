package com.example.place.activity

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AlarmManagerCompat
import androidx.preference.PreferenceManager
import com.example.place.MetaData.Companion.getInstance
import com.example.place.R
import com.example.place.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var alertDialog: AlertDialog? = null
    private var metaData = getInstance()
    private var measurementTime:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myToolbar = binding.toolBar
        setSupportActionBar(myToolbar)
        val mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser
        val currentUserTxt = binding.currentUserTxt
        currentUserTxt.text = String.format("%s\nでログインしています．",currentUser!!.email)

        val startBtn = binding.startBtn
        val metaBtn = binding.metaBtn

        measurementTime = PreferenceManager.getDefaultSharedPreferences(this).getInt("measurementTime",10)


        startBtn.setOnClickListener { //問題画面へ遷移
            //メタデータを選択しないと遷移しないようにする
            if (metaData.quizPattern != null || metaData.labelData != null) {
                startMeasurementAlarm()

                val intent = if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("flashCardMode",false)){
                    Intent(applicationContext, FlashCardActivity::class.java)
                }else{
                    Intent(applicationContext, QuestionActivity::class.java)
                }
                startActivity(intent)
            } else {
                Snackbar.make(binding.root,"メタデータ入力してください", Snackbar.LENGTH_SHORT)
                    .setAction("メタデータ入力") {
                        val inputMetaIntent =
                            Intent(applicationContext, InputNoteActivity::class.java)
                        startActivity(inputMetaIntent)
                    }
                    .show()
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
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun startMeasurementAlarm() {

        // 時間をセットする
        val calendar = Calendar.getInstance()
        // Calendarを使って現在の時間をミリ秒で取得
        calendar.timeInMillis = System.currentTimeMillis()
        // metadataに保存されている分数後に設定
        calendar.add(Calendar.SECOND, measurementTime * 60)

        //時間精度デバック用
        val df: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.JAPAN)
        val date = Date(System.currentTimeMillis())
        Log.d("alarmCheck_start", df.format(date))

        //暗黙的なBroadCast
        val intent = Intent("STOP")
        val pending = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(
                applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )}else{
                PendingIntent.getBroadcast(
                    applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            }

        // アラームをセットする
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        //                        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
        AlarmManagerCompat.setExact(am, AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pending)
        Toast.makeText(
            applicationContext,
            "${measurementTime}分の計測を始めます.", Toast.LENGTH_SHORT
        ).show()
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
        AlertDialog.Builder(this@MainActivity)
            .setCancelable(false)
            .setTitle("確認")
            .setMessage("終了してよろしいですか")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No") { _, _ ->  }.show()
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}