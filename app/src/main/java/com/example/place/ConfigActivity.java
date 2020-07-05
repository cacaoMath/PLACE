package com.example.place;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConfigActivity extends AppCompatActivity {
    protected static final String TAG = ConfigActivity.class.getSimpleName();
    private Spinner question_spinner;
    private Switch confident_switch;
    private Button config_save_btn;
    private String Sum_question, Switch_label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        question_spinner = findViewById(R.id.Sum_of_task_spinner);
        confident_switch = findViewById(R.id.confidence_switch);
        config_save_btn = findViewById(R.id.config_save_btn);

        checkStorage();

        config_save_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Sum_question = question_spinner.getSelectedItem().toString();
                //saveConfig();
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        confident_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Switch_label = "On";
                    Log.d(TAG, "On");
                }
                else{
                    Switch_label = "Off";
                    Log.d(TAG, "Off");
                }
            }
        });
    }
    public void checkStorage() {
        //内部ストレージに存在しているかで分岐
        String text = readFile("MyConfig");
        if (text == null) {
            confident_switch.setChecked(true);
            question_spinner.setSelection(0);
        } else {
            String[] temp = text.split(",", 2);
            switch(temp[0]){
                case "10": question_spinner.setSelection(0);break;
                case "25": question_spinner.setSelection(1);break;
                case "50": question_spinner.setSelection(2);break;
                case "75": question_spinner.setSelection(3);break;
                case "100": question_spinner.setSelection(4);break;
                case "200": question_spinner.setSelection(5);break;
                case "300": question_spinner.setSelection(6);break;
                case "500": question_spinner.setSelection(7);break;
                case "750": question_spinner.setSelection(8);break;
                case "1000": question_spinner.setSelection(9);break;
                case "1300": question_spinner.setSelection(10);break;
            }
            if(temp[1].equals("On")) {
                confident_switch.setChecked(true);
                Switch_label = "On";
            }else{
                confident_switch.setChecked(false);
                Switch_label = "Off";
            }
        }
    }

    public void saveConfig(){
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append(Sum_question);
        sbuilder.append(",");
        sbuilder.append(Switch_label);
        saveFile("MyConfig", sbuilder.toString());
    }

    //ファイル保存
    public void saveFile(String file, String str){
        try (FileOutputStream fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE);){
            fileOutputstream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
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

}