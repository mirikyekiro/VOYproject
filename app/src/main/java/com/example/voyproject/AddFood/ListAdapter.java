package com.example.voyproject.AddFood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.voyproject.MainMenu.FragmentHome;
import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;
import com.example.voyproject.databinding.AddFoodFragBinding;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {

    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);
        MainActivityFood maFood = (MainActivityFood) this.getContext();

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView listName = view.findViewById(R.id.listName);
        TextView listKcal = view.findViewById(R.id.listKcal);

        TextView mealText = maFood.findViewById(R.id.text_meal);

        Button btnAddFood = view.findViewById(R.id.btn_addProduct);

        listName.setText(listData.name);
        listKcal.setText(listData.kcal + " Ккал");

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FragmentHome.class);
                intent.putExtra(mealText.toString(), mealText.toString());
                intent.putExtra(listData.name+"name", listData.name);
                intent.putExtra(listData.name+"kcal", listData.kcal);
                intent.putExtra(listData.name+"protein", listData.protein);
                intent.putExtra(listData.name+"fat", listData.fat);
                intent.putExtra(listData.name+"carbohydrat", listData.carbohydrat);
            }
        });

        return view;
    }
}
