package com.example.place

import android.app.Activity
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

open class MeasurementABReceiver(private val context: Context): BroadcastReceiver() {
    private val dt = DataTransferKt()
    override fun onReceive(context: Context?, intent: Intent?) {
        val df = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SS")
        val date = Date(System.currentTimeMillis())
        Log.d("alarmCheck_stop", df.format(date))
        val builder = AlertDialog.Builder(context)
        builder.setTitle("計測終了")
        builder.setCancelable(false)
        builder.setMessage("規定の計測時間が経過したので\n計測を終了します．\nお疲れさまでした．")
            .setNegativeButton("OK") { _, _ ->
                val activity = this.context as Activity
                activity.finish()
            }.show()
    }
    fun cancelABReceiver(){
        Toast.makeText(this.context, "中止しました", Toast.LENGTH_SHORT).show()
        // アラームの削除
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent("STOP")
        val pendingIntent = PendingIntent.getBroadcast(this.context, 0, intent, 0)

        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        dt.resetDataList()
    }
}