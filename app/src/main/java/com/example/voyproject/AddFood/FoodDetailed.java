package com.example.voyproject.AddFood;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.R;
import com.example.voyproject.databinding.ActivityDetailedBinding;

public class FoodDetailed extends AppCompatActivity {

    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView (binding .getRoot ());

        Intent intent = this.getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int kcal = intent.getIntExtra("kcal", 428);
            float protein = intent.getFloatExtra("protein", 5.4f);
            float fat = intent.getFloatExtra("fat", 26.3f);
            float carbohydrat = intent.getFloatExtra("carbohydrat", 42.4f);

            binding.detailName.setText(name);
            binding.detailKcal.setText(kcal+"");
            binding.detailProtein.setText(Math.round(protein)+"");
            binding.detailFat.setText(Math.round(fat)+"");
            binding.detailCarbo.setText(Math.round(carbohydrat)+"");
        }

        binding.btnBackAddFodd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
