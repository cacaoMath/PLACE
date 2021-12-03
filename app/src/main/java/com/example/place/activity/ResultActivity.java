package com.example.place.activity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.example.place.DataTransferKt;
import com.example.place.MeasurementABReceiver;
import com.example.place.R;
import com.example.place.databinding.ActivityResultBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    protected static final String TAG = ResultActivity.class.getSimpleName();

    private ActivityResultBinding binding;
    private DataTransferKt dt = new DataTransferKt();

    MeasurementABReceiver myReceiver  = new MeasurementABReceiver(this){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            //終了時データを転送する
            dt.sendResultData();
        }
    };

    ArrayList<Integer> Known_words;
    ArrayList<Integer> Mistakes_words;

    Boolean isFlashCardMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);


        TextView numTasks = binding.resultText1;
        TextView rightPer = binding.resultText2;
        Button restartBtn = binding.restartBtn;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isFlashCardMode= sharedPreferences.getBoolean("flashCardMode",false);
        if(isFlashCardMode)
        {
            int[] learnedWordNumList = getIntent().getExtras().getIntArray("LearnedWordNumList");
            long[] learningTimeList = getIntent().getExtras().getLongArray("RememberingOrNotList");
            int[] rememberingOrNotList = getIntent().getExtras().getIntArray("LearningTimeList");
            dt.addFlashCardResultData(learnedWordNumList,learningTimeList,rememberingOrNotList);

            //todo:文言の変更
            numTasks.setText(""+learnedWordNumList.length);
            rightPer.setText("-");
        }else{

            int[] result = getIntent().getExtras().getIntArray("Result");
            int score = getResult(result);
            int[] Q_number = getIntent().getExtras().getIntArray("Q_number");
            long[] learning_time = getIntent().getExtras().getLongArray("Learning_Time");
            int[] confidence_data = getIntent().getExtras().getIntArray("Confidence_data");
            Known_words = new ArrayList();
            Mistakes_words = new ArrayList();


            this.getResultDetail(Q_number, result);

            Log.d(TAG, "data value"+ learning_time[0]);
            numTasks.setText(""+result.length);
            double percent = ((double)score / (double)result.length) * 100;
            rightPer.setText(""+percent + "%");

            //ここでfirestoreに１セット終了時の結果を追加する．
            dt.addSelectQuizResultData(learning_time, confidence_data, Known_words, Mistakes_words, Q_number);    //テストの結果をfireStoreに送信

        }





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
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int id) {
                                        myReceiver.cancelABReceiver();
                                        Log.d(TAG, "onClick:home_btn");
                                        finish();

                                    }
                                }).show();


                    }
                });


        restartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent restartIntent;
                if(isFlashCardMode){
                    restartIntent = new Intent(getApplicationContext(), FlashCardActivity.class);
                }else{
                    restartIntent = new Intent(getApplicationContext(), QuestionActivity.class);
                }
                startActivity(restartIntent);
                Log.d(TAG, "onClick:restart");
                finish();
            }
        });

        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("voiceMode",false)){
            Intent restartIntent;
            restartIntent = new Intent(getApplicationContext(), FlashCardActivity.class);
            startActivity(restartIntent);
            Log.d(TAG, "flash card:restart");
            finish();
        }
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
                Known_words.add(Q_num[i]);
            }
        }
    }

    //正答数の取得
    public int getResult(int[] temp){
        int Num_of_correct = 0;
        for (int j : temp) {
            if (j == 1) {
                Num_of_correct++;
            }
        }
        return Num_of_correct;
    }

}