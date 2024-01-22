package com.example.voyproject.StartScreen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.voyproject.R;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentTwo extends Fragment {
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_HEIGHT = "Height";
    public static final String APP_PREFERENCES_WEIGHT = "Weight";
    Button btnSave;
    EditText editTextName, editTextAge, editTextHeight, editTextWeight;
    SharedPreferences sPref;
    TextInputLayout nameLayout, ageLayout, heightLayout, weightLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();

        View view = inflater.inflate(R.layout.frag2_cp, container, false);

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

        btnSave.setEnabled(false);

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
                    closeKeyboard();
                    Toast.makeText(activity, "Сохранение завершено!",Toast.LENGTH_LONG).show();
                    SaveData();
                    Navigation.findNavController(view).navigate(R.id.action_fragmentTwo_to_fragmentThree);
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
        ed.putInt(APP_PREFERENCES_AGE, Integer.parseInt(editTextAge.getText().toString()));
        ed.putInt(APP_PREFERENCES_HEIGHT, Integer.parseInt(editTextHeight.getText().toString()));
        ed.putInt(APP_PREFERENCES_WEIGHT, Integer.parseInt(editTextWeight.getText().toString()));
        ed.commit();
    }

    private void closeKeyboard(){
        View view = this.getActivity().getCurrentFocus();
        if(view !=null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF9C8F4E")));
                break;
            } else {
                v.setEnabled(true);
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFEFCD25")));
            }
        }
    }
}