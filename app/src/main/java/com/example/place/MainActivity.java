package com.example.place;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.settlingmeasurement.Sensing;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = MainActivity.class.getSimpleName();
    private Quiz quiz;  //英単語問題データクラス
    private  int numOfCorrect;  //正解数
    AlertDialog alertDialog;
    MetaData metaData = MetaData.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);


        Button unknownBtn = findViewById(R.id.unknown_btn);
        Button rememberBtn = findViewById(R.id.remember_btn);
        Button startBtn = findViewById(R.id.start_btn);
        Button vocabBtn = findViewById(R.id.vocabulary_btn);
        Button metaBtn = findViewById(R.id.meta_btn);


        numOfCorrect = 0;
        quiz = new Quiz();
        this.UpdateMemory();//まだよくわからない


        //記憶数によって表示を変化させる
        int unknownWords = quiz.getQuizData().length - numOfCorrect;
        rememberBtn.setText("Remember\n"+numOfCorrect);
        unknownBtn.setText("Unknown\n"+unknownWords);



        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //問題画面へ遷移
                //メタデータを選択しないと遷移しないようにする
                if(metaData.getQuizPattern() != null){
                    Intent qIntent = new Intent(getApplicationContext(), QuestionActivity.class);
                    startActivity(qIntent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"メタデータを入力して下さい",Toast.LENGTH_LONG);
                    // 位置調整
                    toast.setGravity(Gravity.CENTER, 0, -200);
                    toast.show();
                }

                Log.d(TAG, "onClick:start_btn");
            }
        });

        vocabBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //MyWords画面へ遷移
                Intent vocabIntent = new Intent(getApplicationContext(), VocabActivity.class);
                startActivity(vocabIntent);
                Log.d(TAG, "onClick:vocab_btn");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.config:
                //設定画面へ遷移
                Intent configIntent = new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(configIntent);
                Log.d(TAG, "onClick:config_btn");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            String[] temp = text.split(",", quiz.getQuizData().length);
            for (int i = 0; i < quiz.getQuizData().length; i++) {
                if (temp[i].equals("1")) {
                    quiz.setMemory(i, true);
                    numOfCorrect++;
                }
            }
        }
    }

}