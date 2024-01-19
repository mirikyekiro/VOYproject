package com.example.voyproject.StartScreen;

import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;
import android.os.Bundle;

import com.example.voyproject.R;

public class StartScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager. LayoutParams.FLAG_LAYOUT_NO_LIMITS);


    }
}