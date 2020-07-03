package com.example.place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    protected static final String TAG = DetailActivity.class.getSimpleName();
    ArrayList<Integer> Known_words, Mistake_Words, AI_words;
    TextView text1[][], text2[][], text3[][];
    ImageView view1[], view2[], view3[];
    Quiz quiz;
    private String[][] quizdeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        quiz = new Quiz();
        Known_words = getIntent().getExtras().getIntegerArrayList("Known");
        Mistake_Words = getIntent().getExtras().getIntegerArrayList("Mistake");
        AI_words = getIntent().getExtras().getIntegerArrayList("AI");
        quizdeta = quiz.getQuizData();
        text1 = new TextView[Known_words.size()][2]; text2 = new TextView[Mistake_Words.size()][2]; text3 = new TextView[AI_words.size()][2];
        view1 = new ImageView[Known_words.size()]; view2 = new ImageView[Mistake_Words.size()]; view3 = new ImageView[AI_words.size()];

        LinearLayout layout = findViewById(R.id.linearLayout_detail);


        TextView labelview1 = new TextView(this);
        layout.addView(labelview1);
        labelview1.setText("正解単語");
        labelview1.setTextSize(24);
        labelview1.setTextColor(getResources().getColor(R.color.colorDefault));
        labelview1.setGravity(Gravity.CENTER);

        for(int i = 0; i < Known_words.size(); i++){
            View view = getLayoutInflater().inflate(R.layout.sub2, null);
            layout.addView(view);
            view1[i] = view.findViewById(R.id.imageViewmark);
            text1[i][0] = view.findViewById(R.id.Sub2text1);
            text1[i][1] = view.findViewById(R.id.Sub2text2);
            if(quizdeta[Known_words.get(i)-1][2].length() > 12){
                view1[i].getLayoutParams().height = 300;
            }
            text1[i][0].setText("・" + quizdeta[Known_words.get(i)-1][1]);
            text1[i][1].setText("「" + getXXX(quizdeta[Known_words.get(i)-1][2])+ "」");
        }

        for(int i = 0; i < AI_words.size(); i++){
            View view = getLayoutInflater().inflate(R.layout.sub2, null);
            layout.addView(view);
            view3[i] = view.findViewById(R.id.imageViewmark);
            text3[i][0] = view.findViewById(R.id.Sub2text1);
            text3[i][1] = view.findViewById(R.id.Sub2text2);
            view3[i].setImageResource(R.drawable.mi_mark);
            if(quizdeta[AI_words.get(i)-1][2].length() > 12){
                view3[i].getLayoutParams().height = 300;
            }
            text3[i][0].setText("・" + quizdeta[AI_words.get(i)-1][1]);
            text3[i][1].setText("「" + getXXX(quizdeta[AI_words.get(i)-1][2]) + "」");
        }


        TextView labelview3 = new TextView(this);
        layout.addView(labelview3);
        labelview3.setText("MISS単語");
        labelview3.setTextSize(24);
        labelview3.setTextColor(getResources().getColor(R.color.colorDefault));
        labelview3.setGravity(Gravity.CENTER);

        for(int i = 0; i < Mistake_Words.size(); i++){
            View view = getLayoutInflater().inflate(R.layout.sub2, null);
            layout.addView(view);
            view2[i] = view.findViewById(R.id.imageViewmark);
            text2[i][0] = view.findViewById(R.id.Sub2text1);
            text2[i][1] = view.findViewById(R.id.Sub2text2);
            view2[i].setImageResource(R.drawable.mi_mark);
            if(quizdeta[Mistake_Words.get(i)-1][2].length() > 12){
                view2[i].getLayoutParams().height = 300;
            }
            text2[i][0].setText("・" + quizdeta[Mistake_Words.get(i)-1][1]);
            text2[i][1].setText("「" + getXXX(quizdeta[Mistake_Words.get(i)-1][2]) + "」");
        }


    }

    public String getXXX(String target){
        int midleindex = 0;
        int lastindex = target.length();
        if(!target.contains("\n")){
            if(target.length() > 15){
                if(target.contains(")")){
                    midleindex =  target.indexOf(")");
                }
                if(midleindex < 10) {
                    if(target.contains("、")){
                        if(target.indexOf("、") < 7){

                        }else{
                            midleindex =  target.indexOf("、");
                        }
                    }
                }
                return  (target.substring(0, midleindex+1) + "\n　" + target.substring(midleindex+1, lastindex));
            }
            return target;

        }
        else{
            return  target;
        }
    }

}