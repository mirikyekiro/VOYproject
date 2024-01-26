package com.example.voyproject.StartScreen;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;

public class FragmentThree extends Fragment {

    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_TYPE = "Type";
    public static final String APP_PREFERENCES_TYPE_name = "TypeName";
    Button btnSave;
    SharedPreferences sPref;
    RadioGroup rg;
    RadioButton rb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        View view = inflater.inflate(R.layout.frag3_cp, container, false);

        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        btnSave = view.findViewById(R.id.btnSaveType);
        rg = view.findViewById(R.id.radioGroup);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) rg.findViewById(radioId);
                String nameRB = (String) rb.getText();

                ed.putInt(APP_PREFERENCES_TYPE, Integer.valueOf(radioId));
                ed.putString(APP_PREFERENCES_TYPE_name, nameRB);
                ed.commit();


                if(radioId == 2131231143)
                    Navigation.findNavController(view).navigate(R.id.action_fragmentThree_to_fragmentFive);
                else
                    Navigation.findNavController(view).navigate(R.id.action_fragmentThree_to_fragmentFour);
            }
        });

        return view;
    }

    public void checkButton(View view){
        int radioId = rg.getCheckedRadioButtonId();
        rb = view.findViewById(radioId);
    }
}
