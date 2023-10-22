package com.example.project1;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project1.R;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onSettings(View view) {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);


        }
    public void onRecord(View view){
        Intent intent = new Intent(this,recordingActivity.class);
        startActivity(intent);
    }
}