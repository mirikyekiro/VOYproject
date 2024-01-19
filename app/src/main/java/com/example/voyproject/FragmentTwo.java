package com.example.voyproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.time.Duration;

public class FragmentTwo extends Fragment {
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_HEIGHT = "Height";
    public static final String APP_PREFERENCES_WEIGHT = "Weight";
    Button btn, btnSave;
    EditText editTextName, editTextAge, editTextHeight, editTextWeight;
    SharedPreferences sPref;
    TextInputLayout nameLayout, ageLayout, heightLayout, weightLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        btn = view.findViewById(R.id.backToStartActivity);
        btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragmentTwo_to_fragmentOne, null));

        editTextName = view.findViewById(R.id.editName);
        editTextAge = view.findViewById(R.id.age);
        editTextHeight = view.findViewById(R.id.height);
        editTextWeight = view.findViewById(R.id.weight);

        EditText[] edList = {editTextName, editTextAge, editTextHeight, editTextWeight};

        btnSave = view.findViewById(R.id.btnSaveProfile);

        nameLayout = view.findViewById(R.id.nameError);
        ageLayout = view.findViewById(R.id.ageError);
        heightLayout = view.findViewById(R.id.heightError);
        weightLayout = view.findViewById(R.id.weightError);


        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, btnSave);
        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAge = editTextAge.getText().toString();
                int intAge = Integer.parseInt(strAge);

                String strHeight = editTextHeight.getText().toString();
                float intHeight = Float.parseFloat(strHeight);

                String strWeight = editTextWeight.getText().toString();
                float intWeight = Float.parseFloat(strWeight);


                if(intAge < 0 | intAge > 123)
                    ageLayout.setError("Введите соответствующий возраст");
                else if (intHeight < 30 | intHeight > 250) {
                    ageLayout.setError(null);
                    heightLayout.setError("Введите соответствующий рост");
                }
                else if (intWeight < 2 | intWeight > 800) {
                    heightLayout.setError(null);
                    weightLayout.setError("Введите соответствующий вес");
                }
                else {
                    weightLayout.setError(null);
                    Toast.makeText(activity, "Сохранение завершено!",Toast.LENGTH_LONG).show();
                    SaveData();
                    startActivity(new Intent(activity, MainMenu.class));
                }
            }
        });
        return view;
    }


    private void SaveData(){
        Activity activity = getActivity();

        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(APP_PREFERENCES_NAME, editTextName.getText().toString());
        ed.putString(APP_PREFERENCES_AGE, editTextAge.getText().toString());
        ed.putString(APP_PREFERENCES_HEIGHT, editTextHeight.getText().toString());
        ed.putString(APP_PREFERENCES_WEIGHT, editTextWeight.getText().toString());
        ed.commit();
    }
}


class CustomTextWatcher implements TextWatcher {

    View v;
    EditText[] edList;

    public CustomTextWatcher(EditText[] edList, Button v) {
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
                break;
            } else {
                v.setEnabled(true);
            }
        }
    }
}