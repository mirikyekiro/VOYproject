package com.example.voyproject.AddFood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;
import com.example.voyproject.databinding.AddFoodFragBinding;

import java.io.Console;
import java.util.ArrayList;

public class MainActivityFood extends AppCompatActivity {
    AddFoodFragBinding binding;
    SearchView searchView;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ArrayList<ListData> searchList;
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddFoodFragBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle arguments = getIntent().getExtras();

        String textMeal = arguments.get("textMeal").toString();
        TextView mealText = this.findViewById(R.id.text_meal);
        mealText.setText(textMeal);

        Button btnBack = this.findViewById(R.id.btnBackToMenu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String[] nameList = {"Медовик", "Курник", "Хачапури"};
        int[] kcalList = {428, 289, 360};
        float[] proteinList = {5.4f, 9.0f, 10.0f};
        float[] fatList = {26.3f, 13.0f, 22.0f};
        float[] carboList = {42.4f, 13.0f, 30.0f};

        for (int i = 0; i < nameList.length; i++){
            listData = new ListData(nameList[i], kcalList[i], (int) proteinList[i], (int) fatList[i], (int) carboList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter (MainActivityFood.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivityFood.this, "Сохранение завершено!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivityFood.this, FoodDetailed.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("kcal", kcalList[i]);
                intent.putExtra("protein", proteinList[i]);
                intent.putExtra("fat", fatList[i]);
                intent.putExtra("carbohydrat", carboList[i]);
                startActivity(intent);
            }
        });

        searchView = this.findViewById(R.id.search_food);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Button btnAddNewFood = this.findViewById(R.id.btnAddNewProduct);
        btnAddNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityFood.this, NewFood.class));
            }
        });

    }
}
