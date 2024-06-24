package com.example.voyproject.StartScreen;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

public class FragmentOne extends Fragment {

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag1_cp, container, false);

        btn = view.findViewById(R.id.btnCreateProfile);
        btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragmentOne_to_fragmentTwo, null));

        return view;
    }
}