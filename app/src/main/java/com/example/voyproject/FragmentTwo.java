package com.example.voyproject;

import android.content.Context;
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

import com.google.android.material.textfield.TextInputLayout;

public class FragmentTwo extends Fragment {
    Button btn, btnSave;
    EditText editTextName, editTextAge, editTextHeight, editTextWeight;
    SharedPreferences sPref;
    TextInputLayout nameLayout, ageLayout, heightLayout, weightLayout;
    final String SAVED_TEXT = "saved_text";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(TextUtils.isEmpty(editTextName.getText().toString())) {
                    nameLayout.setError("Поле не должно быть пустым.");
                } else nameLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(editTextName.getText().toString())) {
                    nameLayout.setError("Поле не должно быть пустым.");
                } else nameLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(editTextName.getText().toString())) {
                    nameLayout.setError("Поле не должно быть пустым.");
                } else nameLayout.setError(null);
            }
        });

        editTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(TextUtils.isEmpty(editTextAge.getText().toString())) {
                    ageLayout.setError("Поле не должно быть пустым");
                } else if (Integer.parseInt(editTextAge.getText().toString()) < 10 && Integer.parseInt(editTextAge.getText().toString()) >100) {
                    ageLayout.setError("Возраст должен быть от 10 лет");
                } else ageLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(editTextAge.getText().toString())) {
                    ageLayout.setError("Поле не должно быть пустым");
                } else if (Integer.parseInt(editTextAge.getText().toString()) < 10 && Integer.parseInt(editTextAge.getText().toString()) >100) {
                    ageLayout.setError("Возраст должен быть от 10 лет");
                } else ageLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(editTextAge.getText().toString())) {
                    ageLayout.setError("Поле не должно быть пустым");
                } else if (Integer.parseInt(editTextAge.getText().toString()) < 10 && Integer.parseInt(editTextAge.getText().toString()) >100) {
                    ageLayout.setError("Возраст должен быть от 10 лет");
                } else ageLayout.setError(null);
            }
        });

        editTextHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(TextUtils.isEmpty(editTextHeight.getText().toString())) {
                    heightLayout.setError("Поле не должно быть пустым.");
                } else if (Integer.parseInt(editTextHeight.getText().toString()) < 50 && Integer.parseInt(editTextHeight.getText().toString()) >300) {
                    heightLayout.setError("Рост должен быть хотя бы 50см");
                } else heightLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(editTextHeight.getText().toString())) {
                    heightLayout.setError("Поле не должно быть пустым.");
                } else if (Integer.parseInt(editTextHeight.getText().toString()) < 50 && Integer.parseInt(editTextHeight.getText().toString()) >300) {
                    heightLayout.setError("Рост должен быть хотя бы 50см");
                } else heightLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(editTextHeight.getText().toString())) {
                    heightLayout.setError("Поле не должно быть пустым.");
                } else if (Integer.parseInt(editTextHeight.getText().toString()) < 50 && Integer.parseInt(editTextHeight.getText().toString()) >300) {
                    heightLayout.setError("Рост должен быть хотя бы 50см");
                } else heightLayout.setError(null);
            }
        });

        editTextWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(TextUtils.isEmpty(editTextWeight.getText().toString())) {
                    weightLayout.setError("Поле не должно быть пустым.");
                } else if (Float.parseFloat(editTextWeight.getText().toString()) < 35 && Float.parseFloat(editTextWeight.getText().toString()) > 650) {
                    weightLayout.setError("Вес должен быть хотя бы 35.0кг");
                } else weightLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(editTextWeight.getText().toString())) {
                    weightLayout.setError("Поле не должно быть пустым.");
                } else if (Float.parseFloat(editTextWeight.getText().toString()) < 35 && Float.parseFloat(editTextWeight.getText().toString()) > 650) {
                    weightLayout.setError("Вес должен быть хотя бы 35.0кг");
                } else weightLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(editTextWeight.getText().toString())) {
                    weightLayout.setError("Поле не должно быть пустым.");
                } else if (Float.parseFloat(editTextWeight.getText().toString()) < 35 && Float.parseFloat(editTextWeight.getText().toString()) > 650) {
                    weightLayout.setError("Вес должен быть хотя бы 35.0кг");
                } else weightLayout.setError(null);
            }
        });

        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, btnSave);
        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
        return view;
    }

    private void SaveData(){
        sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("Name", editTextName.getText().toString());
        ed.putString("Age", editTextAge.getText().toString());
        ed.putString("Height", editTextHeight.getText().toString());
        ed.putString("Weight", editTextWeight.getText().toString());
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