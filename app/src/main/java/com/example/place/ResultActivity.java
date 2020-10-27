package com.example.place;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    protected static final String TAG = ResultActivity.class.getSimpleName();
    private TextView numtasks, rightper;
    private Button restartBtn;
    private int sum_of_remember, AInoremember, sum_of_all;//PLACEにはいらない？気がするのでとりあえず放置
    private Quiz quiz;
    private long[] Learning_time;
    private DataTransferKt dt = new DataTransferKt();
    private int[] Confidence_data;

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
        saveMemory(Q_number, result);


        /*
        if(checkConfig()){
            storage.SaveConfidence(Q_number, Learning_time, Condidence_data);
        }
        */

        myToolbar.setNavigationIcon(R.drawable.round_home_black_18dp);

        myToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // ナビゲーションアイコンクリック時の処理
                        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(homeIntent);
                        Log.d(TAG, "onClick:home_btn");
                    }
                });


        restartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent restartIntent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(restartIntent);
                Log.d(TAG, "onClick:restart");
            }
        });

        //ここでfirestoreに１セット終了時の結果を送る
        dt.SendResultData(Learning_time, Confidence_data, Known_words, Mistakes_words, Q_number);    //テストの結果をfireStoreに送信
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

    //内部ストレージに記憶結果を保存
    public void saveMemory(int[] Q_num, int[] correct){
        //内部ストレージに存在しているかで分岐
        String text = readFile("MyMemory");
        if(text != null){
            String[] temp = text.split(",", 1300);
            for (int i = 0; i < quiz.getQuizData().length; i++) {
                if(temp[i].equals("1")){
                    quiz.setMemory(i, true);
                    sum_of_all++;
                }
            }
        }

        //正解した問題の記憶フラグを1にする
        for(int i = 0; i < Q_num.length; i++){
            if(correct[i] == 1){
                //解答時間が6秒より短いかどうかで分岐
                if(Learning_time[i] < 6000){
                    quiz.setMemory(Q_num[i]-1, true);
                    sum_of_all++;
                }else{
                    quiz.setMemory(Q_num[i]-1, false);
                }
            }else{
                quiz.setMemory(Q_num[i]-1, false);
            }
        }
        //内部メモリに記憶度合いをセーブする
        StringBuilder sbuilder = new StringBuilder();
        for (int i = 0; i < quiz.getQuizData().length; i++) {
            sbuilder.append(quiz.getMemory(i));
            sbuilder.append(",");
        }
        saveFile("MyMemory", sbuilder.toString());
    }

    //ファイル保存
    public void saveFile(String file, String str){
        try (FileOutputStream fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE);){
            fileOutputstream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ファイルの読み込み
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
    //確信度の取得がOnであるかをチェック
    public boolean checkConfig(){
        boolean flag = true;
        String text = readFile("MyConfig");
        if(text != null){
            String[] temp = text.split(",", 2);
            if(temp[1].equals("On")){
                flag = true;
            }else{
                flag = false;
            }
        }
        return flag;
    }

}