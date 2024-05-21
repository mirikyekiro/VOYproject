package com.example.voyproject.AddFood;


import static com.example.voyproject.Calendar.CalendarUtils.selectedDate;
import static java.lang.Integer.valueOf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodDetailed extends AppCompatActivity {

    Float kcal, protein, fat, carbohydrat, gramm;
    String id, name, textMeal;
    TextView nameText, kcalText, proteinText, fatText, carbohydratText;
    EditText grammText;
    ListAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHelper db = new DatabaseHelper(this);

        setContentView(R.layout.activity_detailed);
        Bundle arguments = getIntent().getExtras();

        id = arguments.get("id").toString();
        name = arguments.get("name").toString();
        kcal = Float.parseFloat(arguments.get("kcal").toString());
        protein = Float.parseFloat(arguments.get("protein").toString());
        fat = Float.parseFloat(arguments.get("fat").toString());
        carbohydrat = Float.parseFloat(arguments.get("carbo").toString());
        gramm = Float.parseFloat(arguments.get("gramm").toString());
        textMeal = arguments.get("textMeal").toString();

        nameText = this.findViewById(R.id.detailName);
        kcalText = this.findViewById(R.id.detailKcal);
        proteinText = this.findViewById(R.id.detailProtein);
        fatText = this.findViewById(R.id.detailFat);
        carbohydratText = this.findViewById(R.id.detailCarbo);
        grammText = this.findViewById(R.id.detailGramm);

        nameText.setText(name);
        kcalText.setText(kcal + " кКал");
        proteinText.setText("Белки:    "+protein );
        fatText.setText("Жиры:    "+fat);
        carbohydratText.setText("Углеводы: "+carbohydrat);
        grammText.setHint(gramm + " грамм");

        Button add = this.findViewById(R.id.btnAdd);
        Button delete = this.findViewById(R.id.btnDelete);
        Button back = this.findViewById(R.id.btnBackAddFodd);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                float gramms = Float.parseFloat(grammText.getText().toString()) / 100;
                db.moveFood(
                        name,
                        (kcal* gramms) + "",
                        (protein* gramms) + "",
                        (fat* gramms) + "",
                        (carbohydrat* gramms) + "",
                        grammText.getText().toString(),
                        textMeal,
                        selectedDate.toString());
                finish();
            }
        });

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
