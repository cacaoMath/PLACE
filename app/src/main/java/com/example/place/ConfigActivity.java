package com.example.place;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConfigActivity extends AppCompatActivity {
    protected static final String TAG = ConfigActivity.class.getSimpleName();
    private Button config_save_btn, signout_btn;
    private EditText measurementTime_Et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        config_save_btn = findViewById(R.id.config_save_btn);
        signout_btn = findViewById(R.id.signoutBtn);
        measurementTime_Et = findViewById(R.id.measurementTimeEt);
        measurementTime_Et.setText("10");


        config_save_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG,"Saved!!");
                finish();
            }
        });

        signout_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                Intent titleIntent = new Intent(getApplicationContext(), TitleActivity.class);
                startActivity(titleIntent);
                finish();
            }
        });

    }

}