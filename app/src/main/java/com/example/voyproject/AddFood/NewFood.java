package com.example.voyproject.AddFood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

public class NewFood extends AppCompatActivity {
    EditText nameEdit, kcalEdit, proteinEdit, fatEdit, carboEdit, grammEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_food);

        nameEdit = this.findViewById(R.id.editNameFood);
        kcalEdit = this.findViewById(R.id.kcalEdit);
        proteinEdit = this.findViewById(R.id.proteinEdit);
        fatEdit = this.findViewById(R.id.fatEdit);
        carboEdit = this.findViewById(R.id.carboEdit);
        grammEdit = this.findViewById(R.id.grammEdit);

        Button btnBack = this.findViewById(R.id.btnBackToMenu2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnSave = this.findViewById(R.id.btnSaveFood);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(NewFood.this);
                db.addFood(nameEdit.getText().toString().trim(),
                        kcalEdit.getText().toString().trim(),
                        proteinEdit.getText().toString().trim(),
                        fatEdit.getText().toString().trim(),
                        carboEdit.getText().toString().trim(),
                        grammEdit.getText().toString().trim());
            }
        });

    }
}


