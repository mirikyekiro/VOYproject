package com.example.voyproject.StartScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;

public class FragmentFive extends Fragment {
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_TYPE_WORKOUT = "TypeWorkout";
    public static final String APP_PREFERENCES_TYPE_WORKOUT_name = "TypeWorkoutName";
    Button btnSave;
    SharedPreferences sPref;
    View radioButton;
    RadioGroup rg;
    int radioButtonID, position;
    RadioButton checkedRadioButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        View view = inflater.inflate(R.layout.frag5_cp, container, false);

        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        btnSave = view.findViewById(R.id.btnSaveType);
        rg = view.findViewById(R.id.radioGroup2);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButtonID = rg.getCheckedRadioButtonId();
                radioButton = rg.findViewById(radioButtonID);
                position = rg.indexOfChild(radioButton);
                checkedRadioButton =  rg.findViewById(radioButtonID);

                ed.putInt(APP_PREFERENCES_TYPE_WORKOUT, position);
                ed.putString(APP_PREFERENCES_TYPE_WORKOUT_name, checkedRadioButton.getText().toString());
                ed.commit();

                startActivity(new Intent(activity, MainMenu.class));
                activity.finish();

            }
        });

        return view;
    }
}