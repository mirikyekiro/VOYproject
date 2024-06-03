package com.example.voyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.StartScreen.StartScreen;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String DATA_SAVE = "DATA_SAVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        boolean data_save = sPref.getBoolean(DATA_SAVE, false);

        if(data_save) {
            startActivity(new Intent(this, MainMenu.class));
            finish();
        }
        else {
            Intent intent2 = new Intent(this, StartScreen.class);
            startActivity(intent2);
            finish();
        }
    }
}
