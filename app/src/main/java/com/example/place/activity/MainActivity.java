package com.example.place.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.AlarmManagerCompat;

import com.example.place.MetaData;
import com.example.place.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth mAuth;

    AlertDialog alertDialog;
    MetaData metaData = MetaData.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        TextView currentUserTxt = findViewById(R.id.currentUserTxt);
        currentUserTxt.setText(currentUser.getEmail() + "\nでログインしています．");

        Button startBtn = findViewById(R.id.start_btn);
        Button metaBtn = findViewById(R.id.meta_btn);


        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //問題画面へ遷移
                //メタデータを選択しないと遷移しないようにする
                if(metaData.getQuizPattern() != null || metaData.getLabelData() != null){
                    startMeasurementAlarm();
                    Intent qIntent = new Intent(getApplicationContext(), QuestionActivity.class);
                    startActivity(qIntent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"メタデータを入力して下さい.",Toast.LENGTH_LONG);
                    // 位置調整
                    toast.setGravity(Gravity.CENTER, 0, -200);
                    toast.show();
                }

                Log.d(TAG, "onClick:start_btn");
            }
        });



        metaBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //メタデータ入力画面へ遷移
                Intent inputMetaIntent = new Intent(getApplicationContext(), InputNoteActivity.class);
                startActivity(inputMetaIntent);
                Log.d(TAG, "onClick:meta_btn");
            }
        });


    }

    //10分計測のためのアラームをセットする
    private void startMeasurementAlarm(){

            // 時間をセットする
            Calendar calendar = Calendar.getInstance();
            // Calendarを使って現在の時間をミリ秒で取得
            calendar.setTimeInMillis(System.currentTimeMillis());
            // metadataに保存されている分数後に設定
            calendar.add(Calendar.SECOND, metaData.getMeasurementTime()*60);

            //時間精度デバック用
            final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            final Date date = new Date(System.currentTimeMillis());
            Log.d("alarmCheck_start", df.format(date));

            //暗黙的なBroadCast
            Intent intent = new Intent("STOP");
            PendingIntent pending = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            // アラームをセットする
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            if(am != null) {
//                        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
                AlarmManagerCompat.setExact(am, AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

                Toast.makeText(getApplicationContext(),
                        metaData.getMeasurementTime()+"分の計測を始めます.", Toast.LENGTH_SHORT).show();
            }

    }

    private void cancelMeasurementAlarm(){
        Toast.makeText(getApplicationContext(), "中断しました", Toast.LENGTH_SHORT).show();
        // アラームの削除
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent("STOP");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        pendingIntent.cancel();
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.config) {//設定画面へ遷移
            Intent configIntent = new Intent(getApplicationContext(), ConfigActivity.class);
            startActivity(configIntent);
            Log.d(TAG, "onClick:config_btn");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //戻るでアプリ終了
    @Override
    public void onBackPressed() {
        alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setCancelable(false)
                .setTitle("確認")
                .setMessage("終了してよろしいですか")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                }).show();
    }


}