package com.example.voyproject;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.voyproject.MainMenu.MainMenu;
import com.google.android.material.textfield.TextInputLayout;

public class ChangeData extends AppCompatActivity {
    EditText editKcal, editProtein, editFats, editCarbo, editBF, editDinner, editLunch, editSnack;
    TextInputLayout errorKcal, errorProtein, errorFats, errorCarbo, errorBF, errorDinner, errorLunch, errorSnack;
    Button btnSave;
    SharedPreferences sPref;
    EditText[] edList;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_KCAL = "Kcal";
    public static final String APP_PREFERENCES_PROTEIN = "Protein";
    public static final String APP_PREFERENCES_FATS = "Fats";
    public static final String APP_PREFERENCES_CARBO = "Carbo";
    public static final String APP_PREFERENCES_BREAKFAST = "BreakFast";
    public static final String APP_PREFERENCES_LUNCH = "Lunch";
    public static final String APP_PREFERENCES_DINNER = "Dinner";
    public static final String APP_PREFERENCES_SNACKS = "Snacks";
    public static final String DATA_SAVE = "DATA_SAVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Activity activity = this;
        setContentView(R.layout.change_data);

        editKcal = findViewById(R.id.editKcal);
        editProtein = findViewById(R.id.editProtein);
        editFats = findViewById(R.id.editFats);
        editCarbo = findViewById(R.id.editCarbo);
        editBF = findViewById(R.id.editBf);
        editDinner = findViewById(R.id.editDinner);
        editLunch = findViewById(R.id.editLunch);
        editSnack = findViewById(R.id.editSnack);

        editKcal.setText(sPref.getFloat(APP_PREFERENCES_KCAL, 0f) + "");
        editProtein.setText(sPref.getFloat(APP_PREFERENCES_PROTEIN, 0f) + "");
        editFats.setText(sPref.getFloat(APP_PREFERENCES_FATS, 0f) + "");
        editCarbo.setText(sPref.getFloat(APP_PREFERENCES_CARBO, 0f) + "");
        editBF.setText(sPref.getFloat(APP_PREFERENCES_BREAKFAST, 0f) + "");
        editDinner.setText(sPref.getFloat(APP_PREFERENCES_LUNCH, 0f) + "");
        editLunch.setText(sPref.getFloat(APP_PREFERENCES_DINNER, 0f) + "");
        editSnack.setText(sPref.getFloat(APP_PREFERENCES_SNACKS, 0f) + "");

        EditText[] edList = {editKcal, editProtein, editFats, editCarbo, editBF, editDinner, editLunch, editSnack};

        btnSave = findViewById(R.id.btnSaveData);

        errorKcal = findViewById(R.id.kcalError);
        errorProtein = findViewById(R.id.proteinError);
        errorFats = findViewById(R.id.fatsError);
        errorCarbo = findViewById(R.id.carboError);
        errorBF = findViewById(R.id.bfError);
        errorDinner = findViewById(R.id.dinnerError);
        errorLunch = findViewById(R.id.lunchError);
        errorSnack = findViewById(R.id.snackError);



        CustomTextWatcher2 textWatcher = new CustomTextWatcher2(edList, btnSave);
        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Сохранение завершено!",Toast.LENGTH_LONG).show();
                SaveData();
                activity.startActivityForResult(new Intent(activity, MainMenu.class), 1);
                finish();
            }
        });

    }

    private void SaveData(){

        sPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        ed.putFloat(APP_PREFERENCES_KCAL, Float.parseFloat(editKcal.getText().toString()));
        ed.putFloat(APP_PREFERENCES_PROTEIN, Float.parseFloat(editProtein.getText().toString()));
        ed.putFloat(APP_PREFERENCES_FATS, Float.parseFloat(editFats.getText().toString()));
        ed.putFloat(APP_PREFERENCES_CARBO, Float.parseFloat(editCarbo.getText().toString()));
        ed.putFloat(APP_PREFERENCES_BREAKFAST, Float.parseFloat(editBF.getText().toString()));
        ed.putFloat(APP_PREFERENCES_LUNCH, Float.parseFloat(editDinner.getText().toString()));
        ed.putFloat(APP_PREFERENCES_DINNER, Float.parseFloat(editLunch.getText().toString()));
        ed.putFloat(APP_PREFERENCES_SNACKS, Float.parseFloat(editSnack.getText().toString()));

        ed.putBoolean(DATA_SAVE, true);

        ed.commit();
    }
}


class CustomTextWatcher2 implements TextWatcher {

    View v;
    EditText[] edList;

    public CustomTextWatcher2(EditText[] edList, Button v) {
        this.v = v;
        this.edList = edList;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        for (EditText editText : edList) {
            if (editText.getText().toString().trim().length() <= 0) {
                v.setEnabled(false);
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF9C8F4E")));
                break;
            } else {
                v.setEnabled(true);
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFEFCD25")));
            }
        }
    }
}
