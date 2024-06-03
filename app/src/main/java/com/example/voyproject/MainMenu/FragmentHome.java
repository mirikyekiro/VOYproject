package com.example.voyproject.MainMenu;

import static com.example.voyproject.Calendar.CalendarUtils.selectedDate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

import com.example.voyproject.AddFood.ListDay;
import com.example.voyproject.AddFood.MainActivityFood;
import com.example.voyproject.Calendar.CalendarUtils;
import com.example.voyproject.ChangeData;
import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;


import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class FragmentHome extends Fragment {
    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_KCAL = "Kcal";
    public static final String APP_PREFERENCES_PROTEIN = "Protein";
    public static final String APP_PREFERENCES_FATS = "Fats";
    public static final String APP_PREFERENCES_CARBO = "Carbo";
    public static final String APP_PREFERENCES_BREAKFAST = "BreakFast";
    public static final String APP_PREFERENCES_LUNCH = "Lunch";
    public static final String APP_PREFERENCES_DINNER = "Dinner";
    public static final String APP_PREFERENCES_SNACKS = "Snacks";

    TextView kcalText, proteinText, fatsText, carbohydratesText, bfKcalText, lunchKcalText, dinnerKcalText, snackKcalText, textMonth, listTextBreakFast, listTextDinner, listTextLunch, listTextSnack;
    ProgressBar pbKcal, pbProtein, pbFats, pbCarbohydrates, firstArgTrans, secondArgTrans, thirdArgTrans, pbBreakfast, pbLunch, pbDinner, pbSnack;
    float proteins, fats, carbohydrates, kCal, bf, lunch, dinner, snack, sumBf, sumLunch, sumDinner, sumSnack, mainSum, sumProtein, sumFat, sumCarbo;
    Button btnBreakfast, btnLunch, btnDinner, btnSnack, backDate, nextDate;
    LinearLayout llBreakfast, llLunch, llDinner, llSnack;
    DatabaseHelper db;
    String date;
    View btn_changeData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DatabaseHelper(getActivity());
        db.create_db();

        Activity activity = this.getActivity();

        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        selectedDate = LocalDate.now();

        textMonth = view.findViewById(R.id.monthDay);

        kCal = sPref.getFloat(APP_PREFERENCES_KCAL, 0f);
        proteins = sPref.getFloat(APP_PREFERENCES_PROTEIN, 0f);
        fats = sPref.getFloat(APP_PREFERENCES_FATS, 0f);
        carbohydrates = sPref.getFloat(APP_PREFERENCES_CARBO, 0f);
        bf = sPref.getFloat(APP_PREFERENCES_BREAKFAST, 0f);
        lunch = sPref.getFloat(APP_PREFERENCES_LUNCH, 0f);
        dinner = sPref.getFloat(APP_PREFERENCES_DINNER, 0f);
        snack = sPref.getFloat(APP_PREFERENCES_SNACKS, 0f);

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

        btnBreakfast = view.findViewById(R.id.btnBreakast);
        btnLunch = view.findViewById(R.id.btnLunch);
        btnDinner = view.findViewById(R.id.btnDinner);
        btnSnack = view.findViewById(R.id.btnSnack);
        backDate = view.findViewById(R.id.lastBtn);
        nextDate = view.findViewById(R.id.nextBtn);

        llBreakfast = view.findViewById(R.id.llBreakfast);
        llLunch = view.findViewById(R.id.llLunch);
        llDinner = view.findViewById(R.id.llDinner);
        llSnack = view.findViewById(R.id.llSnack);

        listTextBreakFast = view.findViewById(R.id.listTextBreakFast);
        listTextDinner = view.findViewById(R.id.listTextDinner);
        listTextLunch = view.findViewById(R.id.listTextLunch);
        listTextSnack = view.findViewById(R.id.listTextSnack);

        btn_changeData = view.findViewById(R.id.btnChangeHome);

        btn_changeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, ChangeData.class));
            }
        });

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

        backDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousDayAction();
            }
        });

        nextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextDayAction();
            }
        });

        //SetTextInCategoty("Breakfast", listTextBreakFast);
        setDatView();
        return view;
    }

    public void loadData()
    {
        sumBf = db.getSum("kcal", "Завтрак", selectedDate.toString());
        sumLunch = db.getSum("kcal", "Обед", selectedDate.toString());
        sumDinner = db.getSum("kcal", "Ужин", selectedDate.toString());
        sumSnack = db.getSum("kcal", "Перекус", selectedDate.toString());
        mainSum = db.getSum("kcal", "", selectedDate.toString());
        sumProtein = db.getSum("protein", "", selectedDate.toString());
        sumFat = db.getSum("fat", "", selectedDate.toString());
        sumCarbo = db.getSum("carbo", "", selectedDate.toString());


        setPBDetail(pbProtein, "protein", proteinText, Math.round(proteins), selectedDate.toString());
        setPBDetail(pbFats, "fat", fatsText, Math.round(fats), selectedDate.toString());
        setPBDetail(pbCarbohydrates, "carbo", carbohydratesText, Math.round(carbohydrates), selectedDate.toString());

        SetText(kcalText, mainSum, kCal, pbKcal);
        SetText(bfKcalText, sumBf, bf, pbBreakfast);
        SetText(lunchKcalText, sumLunch, lunch, pbLunch);
        SetText(dinnerKcalText, sumDinner, dinner, pbDinner);
        SetText(snackKcalText, sumSnack, snack, pbSnack);

    }

    public void SetText(TextView text, float sum, float kcal, ProgressBar pb)
    {
        text.setText(sum +" / " + kcal + " кКал");
        pb.setMax(Math.round(kcal));
        pb.setProgress(Math.round(sum));
    }

    private void SetTextInCategoty(String category, TextView list)
    {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        list.setText(db.getNameFood(category));
    }

    private void setPBDetail(ProgressBar pb, String detail, TextView text, int maxValue, String dateNow)
    {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        Float value = db.getSum(detail, "", dateNow);
        text.setText(value+" / "+maxValue+" гр");
        pb.setMax(360);


        if(Math.round(value)>maxValue)
            pb.setProgress(115);
        else
            pb.setProgress(115*Math.round(value)/maxValue);

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

    public void setDatView()
    {
        textMonth.setText(CalendarUtils.monthDayFromDate(selectedDate));
        date = selectedDate.toString();
        loadData();
    }

    public void previousDayAction(){
        selectedDate = selectedDate.minusDays(1);
        setDatView();
    }

    public void nextDayAction(){
        selectedDate = selectedDate.plusDays(1);
        setDatView();
    }
}