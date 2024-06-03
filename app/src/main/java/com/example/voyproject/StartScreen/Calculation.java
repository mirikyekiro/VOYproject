package com.example.voyproject.StartScreen;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.voyproject.Database.DatabaseHelper;

import java.lang.reflect.AccessibleObject;

public class Calculation {

    public static int sexID, typeID, workoutID, age, typePositionID;
    public static float heightData, weightData;
    public static SharedPreferences sPref;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_SEX = "SexID";
    public static final String APP_PREFERENCES_TYPE = "TypeID";


    public static final String APP_PREFERENCES_TYPE_POSITION = "TypePositionID";


    public static final String APP_PREFERENCES_TYPE_WORKOUT = "TypeWorkout";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_HEIGHT = "Height";
    public static final String APP_PREFERENCES_WEIGHT = "Weight";

    public static final String APP_PREFERENCES_KCAL = "Kcal";
    public static final String APP_PREFERENCES_PROTEIN = "Protein";
    public static final String APP_PREFERENCES_FATS = "Fats";
    public static final String APP_PREFERENCES_CARBO = "Carbo";
    public static final String APP_PREFERENCES_BREAKFAST = "BreakFast";
    public static final String APP_PREFERENCES_LUNCH = "Lunch";
    public static final String APP_PREFERENCES_DINNER = "Dinner";
    public static final String APP_PREFERENCES_SNACKS = "Snacks";
    public static final String DATA_SAVE = "DATA_SAVE";

    public static float kCal, proteins, fats, carbohydrates, sumKcal;

    public static void SaveData(Activity activity){
        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        sexID = sPref.getInt(APP_PREFERENCES_SEX, 0);
        typeID = sPref.getInt(APP_PREFERENCES_TYPE, 0);
        workoutID = sPref.getInt(APP_PREFERENCES_TYPE_WORKOUT, 0);
        age = sPref.getInt(APP_PREFERENCES_AGE, 0);
        typePositionID = sPref.getInt(APP_PREFERENCES_TYPE_POSITION, 0);
        heightData = sPref.getFloat(APP_PREFERENCES_HEIGHT, 0f);
        weightData = sPref.getFloat(APP_PREFERENCES_WEIGHT, 0f);


        if(typeID == 0)
            CalculationOfKcalDesired(sexID, workoutID, age, heightData, weightData);
        else if(typeID == 1)
            CalculationOfKcalGain(sexID, workoutID, age, heightData, weightData);
        else
            CalculationOfKcalSave(sexID, workoutID, age, heightData, weightData);


        proteins = sumKcal * 0.3f /4;
        fats = sumKcal * 0.3f /9;
        carbohydrates = sumKcal * 0.4f /4;

        saveInSPREF(ed);
    }

    public static void CalculationOfKcalDesired(int sex, int workout, int age, float height, float weight){
        if(sex == 0) {
            kCal = 447.6f + (9.2f * weight) + (3.1f * height) - (4.3f * age);
        }
        else {
            kCal = 88.36f + (13.4f * weight) + (4.8f * height) - (5.7f * age);
        }

        float cal = kCal - 500;
        Lifestyle(cal, workoutID);
    }

    public static void CalculationOfKcalGain(int sex, int workout, int age, float height, float weight){
        if(sex == 0) {
            kCal = 447.6f + (9.2f * weight) + (3.1f * height) - (4.3f * age);
        }
        else {
            kCal = 88.36f + (13.4f * weight) + (4.8f * height) - (5.7f * age);
        }

        int cal = Math.round(kCal + 500);
        Lifestyle(cal, workoutID);
    }

    public static void CalculationOfKcalSave(int sex, int workout, int age, float height, float weight){
        if(sex == 0) {
            kCal = 447.6f + (9.2f * weight) + (3.1f * height) - (4.3f * age);
        }
        else {
            kCal = 88.36f + (13.4f * weight) + (4.8f * height) - (5.7f * age);
        }

        Lifestyle(kCal, workoutID);
    }

    public static void Lifestyle(float kcal, int workout){
        switch(workout){
            case 0: kcal *= 1.2f; break;
            case 1: kcal *= 1.375f; break;
            case 2: kcal *= 1.55f; break;
            case 3: kcal *= 1.725f; break;
            case 4: kcal *= 1.9f; break;
            default: kcal=0f; break;
        }

        switch (typePositionID){
            case 2: kcal+=340; break;
            case 3: kcal+=452; break;
        }

        sumKcal = kcal;
    }

    public static void saveInSPREF(SharedPreferences.Editor ed)
    {
        ed.putFloat(APP_PREFERENCES_KCAL, Math.round(sumKcal));
        ed.putFloat(APP_PREFERENCES_BREAKFAST, Math.round(sumKcal*30/100));
        ed.putFloat(APP_PREFERENCES_LUNCH, Math.round(sumKcal*30/100));
        ed.putFloat(APP_PREFERENCES_DINNER, Math.round(sumKcal*25/100));
        ed.putFloat(APP_PREFERENCES_SNACKS, Math.round(sumKcal*15/100));

        ed.putFloat(APP_PREFERENCES_PROTEIN, proteins);
        ed.putFloat(APP_PREFERENCES_FATS, fats);
        ed.putFloat(APP_PREFERENCES_CARBO, carbohydrates);

        ed.putBoolean(DATA_SAVE, true);

        ed.commit();
    }
}
