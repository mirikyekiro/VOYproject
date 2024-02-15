package com.example.voyproject.AddFood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

import java.util.ArrayList;

public class MainActivityFood extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ArrayList<String> food_id, food_name, food_kcal, food_protein, food_fat, food_carbo, food_gramm;
    String textMeal;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);
        Bundle arguments = getIntent().getExtras();
        textMeal = arguments.get("textMeal").toString();

        Button btnBack = findViewById(R.id.btnBackToMenu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        databaseHelper= new DatabaseHelper(MainActivityFood.this);
        food_id = new ArrayList<>();
        food_name = new ArrayList<>();
        food_kcal = new ArrayList<>();
        food_protein = new ArrayList<>();
        food_fat = new ArrayList<>();
        food_carbo = new ArrayList<>();
        food_gramm = new ArrayList<>();

        Button btnAddNewFood = findViewById(R.id.btnAddNewProduct);
        btnAddNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityFood.this, NewFood.class));
            }
        });

        storeDataInArrays();

        listAdapter = new ListAdapter(MainActivityFood.this, food_id, food_name, food_kcal, food_protein, food_fat, food_carbo, food_gramm);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityFood.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = databaseHelper.readAllData();
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
            }
        }
    }

}
