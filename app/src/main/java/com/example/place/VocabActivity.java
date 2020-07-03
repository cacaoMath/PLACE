package com.example.place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class VocabActivity extends AppCompatActivity {
    protected static final String TAG = VocabActivity.class.getSimpleName();
    TextView text[][];
    private Quiz quiz;
    private String[][] quizset;
    private ArrayList<Integer> Known_words_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);
        LinearLayout layout = findViewById(R.id.linearLayoutVocab);


        quiz = new Quiz();
        Known_words_list = new ArrayList<>();
        this.UpdateMemory();
        quizset = quiz.getQuizData();
        text= new TextView[Known_words_list.size()][2];

        for(int i = 0; i < Known_words_list.size(); i++){
            View view = getLayoutInflater().inflate(R.layout.sub, null);
            layout.addView(view);
            text[i][0] = view.findViewById(R.id.Subtext1);
            text[i][1] = view.findViewById(R.id.Subtext2);
            text[i][0].setText("・"+quizset[Known_words_list.get(i)][1]);
            text[i][1].setText("「"+ quizset[Known_words_list.get(i)][2] + "」");

        }

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
                if(temp[i].equals("1")){
                    quiz.setMemory(i, true);
                    Known_words_list.add(i);
                }
            }
        }
    }
}