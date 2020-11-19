package com.example.place;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    protected static final String TAG = ResultActivity.class.getSimpleName();
    private TextView numtasks, rightper;
    private Button restartBtn;
    private int sum_of_remember, AInoremember, sum_of_all;//PLACEにはいらない？気がするのでとりあえず放置
    private Quiz quiz;
    private long[] Learning_time;
    private DataTransferKt dt = new DataTransferKt();
    private int[] Confidence_data;
    //private MetaData metaData = MetaData.getInstance();

    resultActivityABReceiver myReceiver = new resultActivityABReceiver();

    ArrayList<Integer> Known_words;
    ArrayList<Integer> Mistakes_words;;
    ArrayList<Integer> AI_words;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);




        AInoremember = 0;   sum_of_remember = 0;    sum_of_all = 0;
        numtasks = findViewById(R.id.resulttext1);
        rightper = findViewById(R.id.resulttext2);
        restartBtn = findViewById(R.id.restartBtn);
        quiz = new Quiz();

        //確信度についての保存変数
        int[] result = getIntent().getExtras().getIntArray("Result");
        int score = getResult(result);
        int[] Q_number = getIntent().getExtras().getIntArray("Q_number");
        Learning_time = getIntent().getExtras().getLongArray("Learning_Time");
        Confidence_data = getIntent().getExtras().getIntArray("Confidence_data");
        Known_words = new ArrayList();
        Mistakes_words = new ArrayList();
        AI_words = new ArrayList();


        this.getResultDetail(Q_number, result);

        Log.d(TAG, "data value"+Learning_time[0]);
        numtasks.setText(""+result.length);
        double percent = ((double)score / (double)result.length) * 100;
        rightper.setText(""+percent + "%");



        myToolbar.setNavigationIcon(R.drawable.round_home_black_18dp);

        myToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // ナビゲーションアイコンクリック時の処理
                        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("確認");
                        builder.setMessage("ホームに戻ると計測が中止されます．\nよろしいですか？")
                                .setPositiveButton("いいえ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int id) {

                                    }
                                })
                                .setNegativeButton("はい", new DialogInterface.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int id) {

                                        cancelMeasurementAlarm();
                                        Log.d(TAG, "onClick:home_btn");
                                        finish();

                                    }
                                }).show();


                    }
                });


        restartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent restartIntent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(restartIntent);
                Log.d(TAG, "onClick:restart");
                finish();
            }
        });

        //ここでfirestoreに１セット終了時の結果を追加する．
        dt.addResultData(Learning_time, Confidence_data, Known_words, Mistakes_words, Q_number);    //テストの結果をfireStoreに送信
    }

    @Override
    public void onResume() {

        super.onResume();
        registerReceiver(myReceiver, new IntentFilter("STOP"));
    }

    @Override
    public void onPause() {
        super.onPause();
        // ブロードキャストレシーバーを解除する
        unregisterReceiver(myReceiver);
    }

    @Override
    public void onBackPressed() {
        //中身を空にすることで戻るキーが無効化されます。
    }

    //スクロールビューに表示する結果を取得するメソッド
    public void getResultDetail(int[] Q_num, int[] result){

        for (int i = 0; i < Q_num.length; i++) {
            if(result[i] == 0){
                Mistakes_words.add(Q_num[i]);
            }else{
                sum_of_remember++;
                Known_words.add(Q_num[i]);
            }
        }
    }

    //正答数の取得
    public int getResult(int[] temp){
        int Num_of_correct = 0;
        for(int i = 0; i < temp.length; i++){
            if(temp[i] == 1){
                Num_of_correct++;
            }
        }
        return Num_of_correct;
    }


    //ホームを押すと１０分のカウントを解除する
    private void cancelMeasurementAlarm(){
        Toast.makeText(getApplicationContext(), "中止しました", Toast.LENGTH_SHORT).show();
        // アラームの削除
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent("STOP");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        pendingIntent.cancel();
        alarmManager.cancel(pendingIntent);
    }



    //10分間の時間を計測・終了を伝える
    public class resultActivityABReceiver extends BroadcastReceiver {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onReceive(Context context, Intent intent) {
            //終了時データを転送する
            dt.SendResultData();

            final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SS");
            final Date date = new Date(System.currentTimeMillis());
            Log.d("alarmCheck_stop", df.format(date));

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("計測終了");
            builder.setCancelable(false);
            builder.setMessage("規定の計測時間が経過したので\n計測を終了します．\nお疲れさまでした．")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int id) {

                            finish();
                        }
                    }).show();


            //Toast.makeText(context, "終了してください ", Toast.LENGTH_LONG).show();
        }
    }

}