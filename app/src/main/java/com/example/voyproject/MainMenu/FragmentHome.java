package com.example.voyproject.MainMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

import com.example.voyproject.AddFood.ListDay;
import com.example.voyproject.AddFood.MainActivityFood;
import com.example.voyproject.R;

public class FragmentHome extends Fragment {
    int sexID, typeID, workoutID, age;
    float heightData, weightData;
    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_SEX = "SexID";
    public static final String APP_PREFERENCES_TYPE = "TypeID";
    public static final String APP_PREFERENCES_TYPE_WORKOUT = "TypeWorkout";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_HEIGHT = "Height";
    public static final String APP_PREFERENCES_WEIGHT = "Weight";

    TextView kcalText, proteinText, fatsText, carbohydratesText, bfKcalText, lunchKcalText, dinnerKcalText, snackKcalText;
    ProgressBar pbKcal, pbProtein, pbFats, pbCarbohydrates, firstArgTrans, secondArgTrans, thirdArgTrans, pbBreakfast, pbLunch, pbDinner, pbSnack;
    float kCal, proteins, fats, carbohydrates;
    Button btnBreakfast, btnLunch, btnDinner, btnSnack;
    LinearLayout llBreakfast, llLunch, llDinner, llSnack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        Activity activity = getActivity();

        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        sexID = sPref.getInt(APP_PREFERENCES_SEX, 0);
        typeID = sPref.getInt(APP_PREFERENCES_TYPE, 0);
        workoutID = sPref.getInt(APP_PREFERENCES_TYPE_WORKOUT, 0);
        age = sPref.getInt(APP_PREFERENCES_AGE, 0);
        heightData = sPref.getFloat(APP_PREFERENCES_HEIGHT, 0f);
        weightData = sPref.getFloat(APP_PREFERENCES_WEIGHT, 0f);

        kcalText = view.findViewById(R.id.textKcal);
        proteinText = view.findViewById(R.id.textProtein);
        fatsText = view.findViewById(R.id.textFats);
        carbohydratesText = view.findViewById(R.id.textCarbohydrates);

        pbKcal = view.findViewById(R.id.progressKcal);
        pbProtein = view.findViewById(R.id.progressProtein);
        pbFats = view.findViewById(R.id.progressFats);
        pbCarbohydrates = view.findViewById(R.id.progressCarbohydrates);
        firstArgTrans = view.findViewById(R.id.first_trans);
        secondArgTrans = view.findViewById(R.id.second_trans);
        thirdArgTrans = view.findViewById(R.id.third_trans);

        pbBreakfast = view.findViewById(R.id.progressBreakfastCal);
        pbLunch = view.findViewById(R.id.progressLunchCal);
        pbDinner = view.findViewById(R.id.progressDinnerCal);
        pbSnack = view.findViewById(R.id.progressSnackCal);

        bfKcalText = view.findViewById(R.id.progressBreakfastCalText);
        dinnerKcalText = view.findViewById(R.id.progressDinnerCalText);
        lunchKcalText = view.findViewById(R.id.progressLunchCalText);
        snackKcalText = view.findViewById(R.id.progressSnackCalText);

        firstArgTrans.setProgress(30);
        secondArgTrans.setProgress(30);
        thirdArgTrans.setProgress(30);

        if(typeID == 0)
            CalculationOfKcalDesired(sexID, workoutID, age, heightData, weightData);
        else if(typeID == 1)
            CalculationOfKcalGain(sexID, workoutID, age, heightData, weightData);
        else
            CalculationOfKcalSave(sexID, workoutID, age, heightData, weightData);


        btnBreakfast = view.findViewById(R.id.btnBreakast);
        btnLunch = view.findViewById(R.id.btnLunch);
        btnDinner = view.findViewById(R.id.btnDinner);
        btnSnack = view.findViewById(R.id.btnSnack);

        llBreakfast = view.findViewById(R.id.llBreakfast);
        llLunch = view.findViewById(R.id.llLunch);
        llDinner = view.findViewById(R.id.llDinner);
        llSnack = view.findViewById(R.id.llSnack);

        llBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.breakfastName);
                String str = textMeal.getText().toString();
                PostmanInterface(str);
            }
        });
        llLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.lunchName);
                String str = textMeal.getText().toString();
                PostmanInterface(str);
            }
        });
        llDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.dinnerName);
                String str = textMeal.getText().toString();
                PostmanInterface(str);
            }
        });
        llSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.snackName);
                String str = textMeal.getText().toString();
                PostmanInterface(str);
            }
        });

        btnBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.breakfastName);
                String str = textMeal.getText().toString();
                PostmanInterface2(str);
            }
        });
        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.lunchName);
                String str = textMeal.getText().toString();
                PostmanInterface2(str);
            }
        });
        btnDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.dinnerName);
                String str = textMeal.getText().toString();
                PostmanInterface2(str);
            }
        });
        btnSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMeal = view.findViewById(R.id.snackName);
                String str = textMeal.getText().toString();
                PostmanInterface2(str);
            }
        });

        return view;
    }

    private void CalculationOfKcalDesired(int sex, int workout, int age, float height, float weight){
        if(sex == 0) {
            kCal = 447.6f + (9.2f * weight) + (3.1f * height) - (4.3f * age);
        }
        else {
            kCal = 88.36f + (13.4f * weight) + (4.8f * height) - (5.7f * age);
        }

        float cal = kCal - 500;
        Lifestyle(cal, workoutID);
    }

    private void CalculationOfKcalGain(int sex, int workout, int age, float height, float weight){
        if(sex == 0) {
            kCal = 447.6f + (9.2f * weight) + (3.1f * height) - (4.3f * age);
        }
        else {
            kCal = 88.36f + (13.4f * weight) + (4.8f * height) - (5.7f * age);
        }

        int cal = Math.round(kCal + 500);
        Lifestyle(cal, workoutID);
    }

    private void CalculationOfKcalSave(int sex, int workout, int age, float height, float weight){
        if(sex == 0) {
            kCal = 447.6f + (9.2f * weight) + (3.1f * height) - (4.3f * age);
        }
        else {
            kCal = 88.36f + (13.4f * weight) + (4.8f * height) - (5.7f * age);
        }

        Lifestyle(kCal, workoutID);
    }

    private void Lifestyle(float kcal, int workout){
        switch(workout){
            case 0: kcal *= 1.2f; break;
            case 1: kcal *= 1.375f; break;
            case 2: kcal *= 1.55f; break;
            case 3: kcal *= 1.725f; break;
            case 4: kcal *= 1.9f; break;
            default: kcal=0f; break;
        }

        kcalText.setText(0+"/"+Math.round(kcal)+" Ккал");
        pbKcal.setMax(Math.round(kcal));

        proteins = kcal * 0.3f /4;
        fats = kcal * 0.3f /9;
        carbohydrates = kcal * 0.4f /4;

        pbProtein.setMax(Math.round(proteins));
        pbFats.setMax(Math.round(fats));
        pbCarbohydrates.setMax(Math.round(carbohydrates));
        proteinText.setText(0+"/"+Math.round(proteins)+" гр");
        fatsText.setText(0+"/"+Math.round(fats)+" гр");
        carbohydratesText.setText(0+"/"+Math.round(carbohydrates)+" гр");

        int bfKcal = Math.round(kcal*30/100);
        pbBreakfast.setMax(bfKcal);
        bfKcalText.setText("/" + bfKcal + " ккал");

        int lunchKcal = Math.round(kcal*30/100);
        pbLunch.setMax(lunchKcal);
        lunchKcalText.setText("/" + lunchKcal + " ккал");

        int dinnerKcal = Math.round(kcal*25/100);
        pbDinner.setMax(dinnerKcal);
        dinnerKcalText.setText("/" + dinnerKcal + " ккал");

        int snackKcal = Math.round(kcal*15/100);
        pbSnack.setMax(snackKcal);
        snackKcalText.setText("/" + snackKcal + " ккал");
    }

    private void PostmanInterface(String str){
        Intent intent = new Intent(getActivity(), ListDay.class);
        intent.putExtra("textMeal", str);
        startActivity(intent);
    }

    private void PostmanInterface2(String str){
        Intent intent = new Intent(getActivity(), MainActivityFood.class);
        intent.putExtra("textMeal", str);
        startActivity(intent);
    }
}