package com.example.voyproject.MainMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.voyproject.R;
import com.example.voyproject.StartScreen.StartScreen;

public class FragmentProfile extends Fragment {
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_HEIGHT = "Height";
    public static final String APP_PREFERENCES_WEIGHT = "Weight";
    public static final String APP_PREFERENCES_TYPE_name = "TypeName";
    public static final String APP_PREFERENCES_TYPE_WORKOUT_name = "TypeWorkoutName";
    public static final String APP_PREFERENCES_DESWEIGHT = "DesiredWeight";
    public static final String APP_PREFERENCES_SEX_name = "SexName";

    TextView name, age, height, weight, type, lifestyle, desiredWeight, sex;
    Button deleteProfile;

    SharedPreferences sPref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Activity activity = getActivity();
        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        name = view.findViewById(R.id.name);
        age = view.findViewById(R.id.age);
        height = view.findViewById(R.id.height);
        weight = view.findViewById(R.id.weight);
        type = view.findViewById(R.id.typeProfile);
        lifestyle = view.findViewById(R.id.lifestyle);
        desiredWeight = view.findViewById(R.id.weightDesired);
        sex = view.findViewById(R.id.sex);

        deleteProfile = view.findViewById(R.id.deleteProfile);


        name.setText(sPref.getString(APP_PREFERENCES_NAME, ""));
        age.setText("Возраст: " + sPref.getInt(APP_PREFERENCES_AGE, 0));
        height.setText("Рост: " + sPref.getInt(APP_PREFERENCES_HEIGHT, 0));
        weight.setText("Вес: " + sPref.getInt(APP_PREFERENCES_WEIGHT, 0));
        type.setText("Тип: " + sPref.getString(APP_PREFERENCES_TYPE_name, ""));
        lifestyle.setText("Образ жизни: " + sPref.getString(APP_PREFERENCES_TYPE_WORKOUT_name, ""));
        sex.setText("Пол: " + sPref.getString(APP_PREFERENCES_SEX_name, ""));

        if(sPref.contains(APP_PREFERENCES_DESWEIGHT)){
            desiredWeight.setVisibility(View.VISIBLE);
            desiredWeight.setText("Желаемый вес: " + sPref.getInt(APP_PREFERENCES_DESWEIGHT, 0));
        }
        else desiredWeight.setVisibility(View.INVISIBLE);

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoAlert("Вы точно хотите удалить профиль?");
            }
        });

        return view;
    }

    private void showInfoAlert(String text){
        Activity activity = getActivity();
        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Удаление профиля")
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ed.clear();
                        ed.commit();
                        startActivity(new Intent(activity, StartScreen.class));
                        activity.finish();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}