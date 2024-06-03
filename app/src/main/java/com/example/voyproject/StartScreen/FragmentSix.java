package com.example.voyproject.StartScreen;

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

import androidx.fragment.app.Fragment;

import com.example.voyproject.ChangeData;
import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;

public class FragmentSix extends Fragment {
    public Context context;
    int sexID;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_SEX = "SexID";
    public static final String APP_PREFERENCES_TYPE_POSITION = "TypePositionID";
    public static final String APP_PREFERENCES_TYPE_POSITION_name = "TypePositionName";
    public static final String APP_PREFERENCES_TYPE_DIABET = "TypeDiabetID";
    public static final String APP_PREFERENCES_TYPE_DIABET_name = "TypeDiabetName";
    public static final String APP_PREFERENCES_TYPE_HBP = "HBP";
    public static final String APP_PREFERENCES_TYPE_GSD = "GSD";
    public static final String DATA_SAVE = "DATA_SAVE";
    Button btnSave;
    SharedPreferences sPref;
    RadioButton preg1RadioButton, preg2RadioButton, preg3RadioButton, hbpRadioButton, gsdRadioButton;
    int radioButtonID1, radioButtonID2, position1, position2;
    View radioButton1, radioButton2;
    RadioGroup rg1, rg2;
    RadioButton checkedRadioButton1, checkedRadioButton2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        View view = inflater.inflate(R.layout.frag6_cp, container, false);

        sPref = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        btnSave = view.findViewById(R.id.btnStart);
        preg1RadioButton = view.findViewById(R.id.pregnancy1);
        preg2RadioButton = view.findViewById(R.id.pregnancy2);
        preg3RadioButton = view.findViewById(R.id.pregnancy3);
        hbpRadioButton = view.findViewById(R.id.hbp);
        gsdRadioButton = view.findViewById(R.id.gsd);
        rg1 = view.findViewById(R.id.radioGroup3);
        rg2 = view.findViewById(R.id.radioGroup4);

        sexID = sPref.getInt(APP_PREFERENCES_SEX, 0);

        if(sexID == 1)
        {
            preg1RadioButton.setEnabled(false);
            preg2RadioButton.setEnabled(false);
            preg3RadioButton.setEnabled(false);
            gsdRadioButton.setEnabled(false);
        }
        else {
            preg1RadioButton.setEnabled(true);
            preg2RadioButton.setEnabled(true);
            preg3RadioButton.setEnabled(true);
            gsdRadioButton.setEnabled(true);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hbpRadioButton.isChecked())
                    ed.putString(APP_PREFERENCES_TYPE_HBP, "ХБП/ХПН");
                else ed.putString(APP_PREFERENCES_TYPE_HBP, "");

                if(gsdRadioButton.isChecked())
                    ed.putString(APP_PREFERENCES_TYPE_GSD, "Гистационно-сахарный диабет");
                else ed.putString(APP_PREFERENCES_TYPE_GSD, "");

                if(rg1.getCheckedRadioButtonId() != -1)
                {
                    radioButtonID1 = rg1.getCheckedRadioButtonId();
                    radioButton1 = rg1.findViewById(radioButtonID1);
                    position1 = rg1.indexOfChild(radioButton1);
                    checkedRadioButton1 =  rg1.findViewById(radioButtonID1);

                    ed.putInt(APP_PREFERENCES_TYPE_POSITION, position1);
                    ed.putString(APP_PREFERENCES_TYPE_POSITION_name, checkedRadioButton1.getText().toString());
                }
                else
                {
                    ed.putInt(APP_PREFERENCES_TYPE_POSITION, 0);
                    ed.putString(APP_PREFERENCES_TYPE_POSITION_name, "");
                }

                if(rg2.getCheckedRadioButtonId() != -1)
                {
                    radioButtonID2 = rg2.getCheckedRadioButtonId();
                    radioButton2 = rg2.findViewById(radioButtonID2);
                    position2 = rg2.indexOfChild(radioButton2);
                    checkedRadioButton2 =  rg2.findViewById(radioButtonID2);

                    ed.putInt(APP_PREFERENCES_TYPE_DIABET, position2);
                    ed.putString(APP_PREFERENCES_TYPE_DIABET_name, checkedRadioButton2.getText().toString());
                }
                else
                {
                    ed.putInt(APP_PREFERENCES_TYPE_DIABET, 0);
                    ed.putString(APP_PREFERENCES_TYPE_DIABET_name, "");
                }

                Calculation.SaveData(activity);

                if(hbpRadioButton.isChecked()||gsdRadioButton.isChecked()||position2 != 1)
                {
                    startActivity(new Intent(activity, ChangeData.class));
                    ed.putBoolean(DATA_SAVE, false);
                }
                else
                {
                    activity.startActivityForResult(new Intent(activity, MainMenu.class), 1);
                    ed.putBoolean(DATA_SAVE, true);
                }

                ed.commit();
                activity.finish();

            }
        });

        return view;
    }
}
