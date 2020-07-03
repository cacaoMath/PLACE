package com.example.place;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;
import java.util.Date;

public class Device_Receiver extends BroadcastReceiver {
    protected static final String TAG = Device_Receiver.class.getSimpleName();
    private AREASQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public void onReceive(Context cn, Intent intent){
        String str = intent.getAction();
        switch(str){
            case Constants.BROADCAST_DETECTED_ACTIVITY:{
                int type = intent.getIntExtra("type", -1);
                int confidence = intent.getIntExtra("confidence", 0);
                String label = "None";//本来はユーザーの状態をラベル付けしているが今は取り合えずこうする
                //一時的にSQLiteに保存
                if(helper == null){
                    helper = new AREASQLiteOpenHelper(cn);
                }
                if(db == null){
                    db = helper.getWritableDatabase();
                }
                this.insertData(db, label, confidence);
                break;
            }
        }
    }

    private void insertData(SQLiteDatabase db, String activity, int confidence){
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(System.currentTimeMillis()); // 現在時刻を取得
        Date date = cl.getTime();
        ContentValues values = new ContentValues();
        values.put("Calendar", String.valueOf(date));
        values.put("Activity", activity);
        values.put("Confidence", confidence);
        db.insert("Activity_Type", null, values);
    }
}
