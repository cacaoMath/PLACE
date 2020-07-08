package com.example.place;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class QuestionActivity extends AppCompatActivity {
    protected static final String TAG = QuestionActivity.class.getSimpleName();
    private boolean eventFlag; //ボタン操作で二重タップを防ぐため

    private int numOfQuiz = 10; //後々はユーザーに決めてもらう
    private int count;
    private Quiz quiz;
    private String[][] quizSet;

    private TextView questionView;
    private Button ansBtn1;
    private Button ansBtn2;
    private Button ansBtn3;
    private Button ansBtn4;

    private String Right_Answer;
    private String  Select_Answer;
    private int[] Result, Q_num;
    private Calendar firstTime, secondTime;
    private long[] learningTime;
    private int[] confidenceData;
    protected IntentFilter intentFilter = new IntentFilter();

    //デバイスステータス取得用
    private BroadcastReceiver sb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        firstTime = Calendar.getInstance(); secondTime = Calendar.getInstance();
        count = 0;
        quiz = new Quiz();
        this.UpdateMemory();
        this.UpdateByConfig();

        Result = new int[numOfQuiz];
        Q_num = new int[numOfQuiz];
        learningTime = new long[numOfQuiz];
        confidenceData = new int[numOfQuiz];

        quizSet = quiz.GetQuizSet(numOfQuiz);

        questionView = findViewById(R.id.Question);
        ansBtn1 = findViewById(R.id.ansBtn1);
        ansBtn2 = findViewById(R.id.ansBtn2);
        ansBtn3 = findViewById(R.id.ansBtn3);
        ansBtn4 = findViewById(R.id.ansBtn4);

        showNextQuiz(); //第１問目表示用

    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確認");
        builder.setMessage("学習の途中ですが，\n終了してよろしいですか？")
                .setPositiveButton("いいえ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                    }
                })
                .setNegativeButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);

                        finish();
                    }
                }).show();
    }

    public void checkAnswer(View view){
        Button answerBtn = findViewById(view.getId());
        Select_Answer = answerBtn.getText().toString();
        secondTime.setTimeInMillis(System.currentTimeMillis()); // 現在時刻を取得

        //ボタン(イベント)連打防止処理
        if(eventFlag) return;
        else{
            eventFlag = true;
            //n秒後にフラグをtrueにする
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    eventFlag = false;
                }
            }, 1000L);
        }

        Log.d(TAG, "onClick:Ans_btn1");
        learningTime[count] =  secondTime.getTimeInMillis() - firstTime.getTimeInMillis() ;

        String alertTitle;
        if(Select_Answer.equals(Right_Answer)){
            alertTitle = "正解!";
            Result[count] = 1;
        }else{
            alertTitle = "不正解...";
            Result[count] = 0;
        }
        Q_num[count] = Integer.valueOf(quizSet[count][7]).intValue();
        // ダイアログを作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え : " + Right_Answer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (count == numOfQuiz - 1) {
                    // 結果画面へ移動
                    Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                    resultIntent.putExtra("Result", Result);
                    resultIntent.putExtra("Q_number", Q_num);
                    resultIntent.putExtra("Learning_Time", learningTime);
                    resultIntent.putExtra("Confidence_data", confidenceData);
                    startActivity(resultIntent);
                } else {
                    count++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showNextQuiz(){
        questionView.setText(quizSet[count][1]);
        if(quizSet[count][1].length() > 14){
            questionView.setTextSize(30);
        }else{
            questionView.setTextSize(40);
        }
        if(quizSet[count][2].length() > 15){
            ansBtn1.setText(getXXX(quizSet[count][2]));
        }else{
            ansBtn1.setText(quizSet[count][2]);
        }
        if(quizSet[count][3].length() > 15){
            ansBtn2.setText(getXXX(quizSet[count][3]));
        }else{
            ansBtn2.setText(quizSet[count][3]);
        }
        if(quizSet[count][4].length() > 15){
            ansBtn3.setText(getXXX(quizSet[count][4]));
        }else{
            ansBtn3.setText(quizSet[count][4]);
        }
        if(quizSet[count][5].length() > 15){
            ansBtn4.setText(getXXX(quizSet[count][5]));
        }else{
            ansBtn4.setText(quizSet[count][5]);
        }
        if(quizSet[count][6].length() > 15){
            Right_Answer = getXXX(quizSet[count][6]);
        }else{
            Right_Answer = (quizSet[count][6]);
        }
        firstTime.setTimeInMillis(System.currentTimeMillis()); // 現在時刻を取得
    }

    public String getXXX(String target){
        int middleIndex = 0;
        int lastIndex = target.length();
        if(!target.contains("\n")){
            if(target.contains(")")){
                middleIndex =  target.indexOf(")");
                if(middleIndex < 10) {
                    if(target.contains("、")){
                        if(target.indexOf("、") < 5){

                        }else{
                            middleIndex =  target.indexOf("、");
                        }
                    }
                }
            }
            else if(target.contains("、")){
                middleIndex =  target.indexOf("、");
            }

            return  (target.substring(0, middleIndex+1) + "\n" + target.substring(middleIndex+1, lastIndex));
        }else{return  target;}
    }

    public String readFile(String file){
        String text = null;

        try (FileInputStream fileInputStream = openFileInput(file);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))) {

            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                text = lineBuffer ;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    //内部ストレージに入っている記憶データを用いてフラグを更新
    public void UpdateMemory() {
        //内部ストレージに存在しているかで分岐
        String text = readFile("MyMemory");
        if (text == null) {
        } else {
            String[] temp = text.split(",", 1300);
            for (int i = 0; i < quiz.getQuizData().length; i++) {
                if (temp[i].equals("1"))
                    quiz.setMemory(i, true);
            }
        }
    }

    public void UpdateByConfig() {
        //内部ストレージに存在しているかで分岐
        String text = readFile("MyConfig");
        if (text == null) {
            numOfQuiz = 10;
        } else {
            String[] temp = text.split(",", 2);
            numOfQuiz = Integer.valueOf(temp[0]);
        }
    }

    public void SetConfidence(int value){
        confidenceData[count] = value;
    }


    public static class ConfidenceDialogFragment extends DialogFragment{
        private String[] menuList = {"もう完璧！！","選択肢があればなんとか...","なんとなく"};
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("この問題の自信度は？");
            builder.setItems(menuList, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int idx) {
                    // 選択１
                    if (idx == 0) {
                        DoNext(0);
                    }
                    // 選択２
                    else if (idx == 1) {
                        DoNext(1);
                    }
                    // 選択３, idx == 2
                    else{
                        DoNext(2);
                    }
                }
            });

            return builder.create();
        }

        private void DoNext(int value){
            QuestionActivity qActivity = (QuestionActivity) getActivity();
            qActivity.SetConfidence(value);
            //qActivity.checkAnswer();
        }
    }
}