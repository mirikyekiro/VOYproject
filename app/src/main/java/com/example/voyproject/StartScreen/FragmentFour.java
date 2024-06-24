package com.example.voyproject.StartScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentFour extends Fragment {

    EditText editWeight;
    TextInputLayout errorWeight;
    Button btnStart;
    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_TYPE = "TypeID";
    public static final String APP_PREFERENCES_WEIGHT = "Weight";
    public static final String APP_PREFERENCES_DESWEIGHT = "DesiredWeight";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        View view = inflater.inflate(R.layout.frag4_cp, container, false);

        editWeight = view.findViewById(R.id.editWeight);
        errorWeight = view.findViewById(R.id.weightError);
        btnStart = view.findViewById(R.id.btnSave);

        int id = sPref.getInt(APP_PREFERENCES_TYPE, 0);
        float weight = sPref.getFloat(APP_PREFERENCES_WEIGHT, 0);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == 1)
                {
                    if(TextUtils.isEmpty(editWeight.getText().toString()))
                        errorWeight.setError("Введите значение!");
                    else if (Float.parseFloat(editWeight.getText().toString()) < weight)
                        errorWeight.setError("Введите значение больше вашему указанному весу!");
                    else {
                        ed.putFloat(APP_PREFERENCES_DESWEIGHT, Float.parseFloat(editWeight.getText().toString()));
                        ed.commit();
                        Navigation.findNavController(view).navigate(R.id.action_fragmentFour_to_fragmentFive);
                    }
                }
                else {
                    if(TextUtils.isEmpty(editWeight.getText().toString()))
                        errorWeight.setError("Введите значение!");
                    else if (Float.parseFloat(editWeight.getText().toString()) > weight)
                        errorWeight.setError("Введите значение меньше вашему указанному весу!");
                    else {
                        ed.putFloat(APP_PREFERENCES_DESWEIGHT, Float.parseFloat(editWeight.getText().toString()));
                        ed.commit();
                        Navigation.findNavController(view).navigate(R.id.action_fragmentFour_to_fragmentFive);
                    }
                }

            }
        });



        return view;
    }
}
