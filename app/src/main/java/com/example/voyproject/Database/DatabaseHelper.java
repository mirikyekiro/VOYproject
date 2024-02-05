package com.example.voyproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public DatabaseHelper(@Nullable Context context) {
        super(context, FoodConstants.DB_NAME, null, FoodConstants.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FoodConstants.TABLE_FOODLIST_STRUCTURE);
        db.execSQL(FoodConstants.TABLE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FoodConstants.TABLE_NAME_FOODLIST);
        onCreate(db);
    }

    public void addFood(String name, int kcal, int protein, int fat, int carbo, int gramm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FoodConstants.FOOD_NAME, name);
        cv.put(FoodConstants.FOOD_KCAL, kcal);
        cv.put(FoodConstants.FOOD_PROTEIN, protein);
        cv.put(FoodConstants.FOOD_FAT, fat);
        cv.put(FoodConstants.FOOD_CARBO, carbo);
        cv.put(FoodConstants.FOOD_GRAMM, gramm);
        long result = db.insert(FoodConstants.TABLE_NAME_FOODLIST, null, cv);
        if(result == -1)
            Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Блюдо добавлено", Toast.LENGTH_LONG).show();
    }
}
