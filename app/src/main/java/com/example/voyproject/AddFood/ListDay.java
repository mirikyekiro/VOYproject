package com.example.voyproject.AddFood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

import java.util.ArrayList;

public class ListDay extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ArrayList<String> food_id, food_name, food_kcal, food_protein, food_fat, food_carbo, food_gramm, food_category, food_date;
    ProfileListAdapter listAdapter;
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
                Intent intent = new Intent(ListDay.this, MainActivityFood.class);
                intent.putExtra("textMeal", textMeal);
                startActivity(intent);
            }
        });

        recyclerView = this.findViewById(R.id.recyclerView);
        databaseHelper= new DatabaseHelper(ListDay.this);
        food_id = new ArrayList<>();
        food_name = new ArrayList<>();
        food_kcal = new ArrayList<>();
        food_protein = new ArrayList<>();
        food_fat = new ArrayList<>();
        food_carbo = new ArrayList<>();
        food_gramm = new ArrayList<>();
        food_category = new ArrayList<>();
        food_date = new ArrayList<>();

        storeDataInArrays(textMeal);

        listAdapter = new ProfileListAdapter(ListDay.this, this, food_id, food_name, food_kcal, food_protein, food_fat, food_carbo, food_gramm, textMeal);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListDay.this));
    }

    void storeDataInArrays(String textMeal){
        Cursor cursor = databaseHelper.readAllDataCATEGORYLIST(textMeal);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){
                food_id.add(cursor.getString(0));
                food_name.add(cursor.getString(1));
                food_kcal.add(cursor.getString(2));
                food_protein.add(cursor.getString(3));
                food_fat.add(cursor.getString(4));
                food_carbo.add(cursor.getString(5));
                food_gramm.add(cursor.getString(6));
                food_category.add(cursor.getString(7));
                food_date.add(cursor.getString(8));
            }
        }
    }
}
