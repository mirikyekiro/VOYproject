package com.example.voyproject.AddFood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voyproject.MainMenu.MainMenu;
import com.example.voyproject.R;

public class Group extends AppCompatActivity {
    ImageButton alco, frukti, hleb, krupi, maslo, moloch, myaso, ovoshi, ptica, ryba, sahar, soki, subprod, syry;
    String textMeal;
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);

        activity = this;

        Bundle arguments = getIntent().getExtras();
        textMeal = arguments.get("textMeal").toString();

        alco = findViewById(R.id.btnAlco);
        frukti = findViewById(R.id.btnFrykti);
        hleb = findViewById(R.id.btnHleb);
        krupi = findViewById(R.id.btnKrypi);
        maslo = findViewById(R.id.btnMaslo);
        moloch = findViewById(R.id.btnMoloch);
        myaso = findViewById(R.id.btnMyaso);
        ovoshi = findViewById(R.id.btnOvoshi);
        ptica = findViewById(R.id.btnPtica);
        ryba = findViewById(R.id.btnRyba);
        sahar = findViewById(R.id.btnSahar);
        soki = findViewById(R.id.btnSoki);
        subprod = findViewById(R.id.btnSubProd);
        syry = findViewById(R.id.btnSyri);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnAlco: open("Алкоголь"); break;
                    case R.id.btnHleb: open("Хлеб"); break;
                    case R.id.btnKrypi: open("Орехи и крупы"); break;
                    case R.id.btnMaslo: open("Масла"); break;
                    case R.id.btnMoloch: open("Молочные продукты"); break;
                    case R.id.btnMyaso: open("Мясо"); break;
                    case R.id.btnOvoshi: open("Овощи"); break;
                    case R.id.btnPtica: open("Птица"); break;
                    case R.id.btnRyba: open("Рыба"); break;
                    case R.id.btnSahar: open("Сахар"); break;
                    case R.id.btnSoki: open("Соки"); break;
                    case R.id.btnSubProd: open("Субпродукты"); break;
                    case R.id.btnSyri: open("Сыры"); break;
                    case R.id.btnFrykti: open("Фрукты"); break;
                }
            }
        };

        alco.setOnClickListener(onClickListener);
        frukti.setOnClickListener(onClickListener);
        hleb.setOnClickListener(onClickListener);
        krupi.setOnClickListener(onClickListener);
        maslo.setOnClickListener(onClickListener);
        moloch.setOnClickListener(onClickListener);
        myaso.setOnClickListener(onClickListener);
        ovoshi.setOnClickListener(onClickListener);
        ptica.setOnClickListener(onClickListener);
        ryba.setOnClickListener(onClickListener);
        sahar.setOnClickListener(onClickListener);
        soki.setOnClickListener(onClickListener);
        subprod.setOnClickListener(onClickListener);
        syry.setOnClickListener(onClickListener);

        Button btnBack = findViewById(R.id.btnBackToMenu5);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(activity, MainMenu.class), 0);
            }
        });
    }

    public void open(String str)
    {
        Intent intent = new Intent(activity, MainActivityFood.class);
        intent.putExtra("textMeal", textMeal);
        intent.putExtra("group", str);
        startActivity(intent);
    }

}
