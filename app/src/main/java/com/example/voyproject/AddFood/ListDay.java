package com.example.voyproject.AddFood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.R;

public class ListDay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_food);
        Bundle arguments = getIntent().getExtras();
        String textMeal = arguments.get("textMeal").toString();
        TextView mealText = findViewById(R.id.text_meal);
        mealText.setText(textMeal);

        Button btnBack = findViewById(R.id.btnBackToMenu2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnOpen = findViewById(R.id.btnAddProduct);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListDay.this, MainActivityFood.class));
            }
        });
    }
}
