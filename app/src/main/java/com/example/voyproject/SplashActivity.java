package com.example.voyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.StartScreen.StartScreen;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_TYPE = "Type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        if(sPref.contains(APP_PREFERENCES_TYPE)) {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent2 = new Intent(this, StartScreen.class);
            startActivity(intent2);
            finish();
        }
    }
}
