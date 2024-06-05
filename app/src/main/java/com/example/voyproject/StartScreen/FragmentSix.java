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
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.example.voyproject.ChangeData;
import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;

public class FragmentSix extends Fragment {
    public Context context;
    int sexID;
    public static final String APP_PREFERENCES = "myProfile";
    public static final String APP_PREFERENCES_SEX = "SexID";
    public static final String APP_PREFERENCES_TYPE_POSITION_name = "TypePositionName";
    public static final String APP_PREFERENCES_TYPE_DIABET_name = "TypeDiabetName";
    public static final String APP_PREFERENCES_TYPE_HBP = "HBP";
    public static final String APP_PREFERENCES_TYPE_GSD = "GSD";
    public static final String DATA_SAVE = "DATA_SAVE";
    Button btnSave;
    SharedPreferences sPref;
    ToggleButton preg1RadioButton, preg2RadioButton, preg3RadioButton, hbpRadioButton, gsdRadioButton, diabet1RadioButton, diabet2RadioButton, diabet3RadioButton;
    ToggleButton pregBtn = null, diabetBtn = null;
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
        diabet1RadioButton = view.findViewById(R.id.diabet1);
        diabet2RadioButton = view.findViewById(R.id.diabet2);
        diabet3RadioButton = view.findViewById(R.id.diabet3);

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

        ToggleButton[] tb1 = {preg1RadioButton,preg2RadioButton,preg3RadioButton};
        ToggleButton[] tb2 = {diabet1RadioButton,diabet2RadioButton, diabet3RadioButton};

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.pregnancy1: pregBtn = CheckBtn(preg1RadioButton, tb1, pregBtn); break;
                    case R.id.pregnancy2: pregBtn = CheckBtn(preg2RadioButton, tb1, pregBtn); break;
                    case R.id.pregnancy3: pregBtn = CheckBtn(preg3RadioButton, tb1, pregBtn); break;
                    case R.id.diabet1: diabetBtn = CheckBtn(diabet1RadioButton, tb2, diabetBtn); break;
                    case R.id.diabet2: diabetBtn = CheckBtn(diabet2RadioButton, tb2, diabetBtn); break;
                    case R.id.diabet3: diabetBtn = CheckBtn(diabet3RadioButton, tb2, diabetBtn); break;
                }
            }
        };

        preg1RadioButton.setOnClickListener(onClickListener);
        preg2RadioButton.setOnClickListener(onClickListener);
        preg3RadioButton.setOnClickListener(onClickListener);
        diabet1RadioButton.setOnClickListener(onClickListener);
        diabet2RadioButton.setOnClickListener(onClickListener);
        diabet3RadioButton.setOnClickListener(onClickListener);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hbpRadioButton.isChecked())
                    ed.putString(APP_PREFERENCES_TYPE_HBP, "ХБП/ХПН");
                else ed.putString(APP_PREFERENCES_TYPE_HBP, "");

                if(gsdRadioButton.isChecked())
                    ed.putString(APP_PREFERENCES_TYPE_GSD, "Гистационно-сахарный диабет");
                else ed.putString(APP_PREFERENCES_TYPE_GSD, "");

                if(pregBtn != null)
                    ed.putString(APP_PREFERENCES_TYPE_POSITION_name, pregBtn.getTextOn().toString());
                else ed.putString(APP_PREFERENCES_TYPE_POSITION_name, "");

                if(diabetBtn != null)
                    ed.putString(APP_PREFERENCES_TYPE_DIABET_name, diabetBtn.getTextOn().toString());
                else ed.putString(APP_PREFERENCES_TYPE_DIABET_name, "");

                Calculation.SaveData(activity);

                if(hbpRadioButton.isChecked()||gsdRadioButton.isChecked())
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

    public ToggleButton CheckBtn(ToggleButton mainBtn, ToggleButton[] btn, ToggleButton saveBtn)
    {
        for(int i= 0; i< btn.length; i++)
        {
            btn[i].setChecked(false);
        }
        mainBtn.setChecked(true);
        saveBtn = mainBtn;
        return saveBtn;
    }

}
