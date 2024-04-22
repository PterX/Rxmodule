package com.hepta.textapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;



import java.io.File;
import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity {

    static public boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text1);
        textView.setText("rxposed app text : "+status);
    }

}