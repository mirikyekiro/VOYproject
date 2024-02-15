package com.example.voyproject.AddFood;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

public class FoodDetailed extends AppCompatActivity {

    String id, name, kcal, protein, fat, carbohydrat, gramm;
    TextView nameText, kcalText, proteinText, fatText, carbohydratText;
    EditText grammText;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Bundle arguments = getIntent().getExtras();

        id = arguments.get("id").toString();
        name = arguments.get("name").toString();
        kcal = arguments.get("kcal").toString();
        protein = arguments.get("protein").toString();
        fat = arguments.get("fat").toString();
        carbohydrat = arguments.get("carbo").toString();
        gramm = arguments.get("gramm").toString();

        nameText = this.findViewById(R.id.detailName);
        kcalText = this.findViewById(R.id.detailKcal);
        proteinText = this.findViewById(R.id.detailProtein);
        fatText = this.findViewById(R.id.detailFat);
        carbohydratText = this.findViewById(R.id.detailCarbo);
        grammText = this.findViewById(R.id.detailGramm);

        nameText.setText(name);
        kcalText.setText(kcal + " кКал");
        proteinText.setText(protein + " белков");
        fatText.setText(fat + " жиров");
        carbohydratText.setText(carbohydrat + " углеводов");
        grammText.setHint(gramm + " грамм");

        Button add = this.findViewById(R.id.btnAdd);
        Button delete = this.findViewById(R.id.btnDelete);
        Button back = this.findViewById(R.id.btnBackAddFodd);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDialog();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void ConfirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper myDB = new DatabaseHelper(FoodDetailed.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

}
