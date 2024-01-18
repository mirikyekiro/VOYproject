package com.example.voyproject;

import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;
import android.os.Bundle;

public class StartScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager. LayoutParams.FLAG_LAYOUT_NO_LIMITS);


    }
}