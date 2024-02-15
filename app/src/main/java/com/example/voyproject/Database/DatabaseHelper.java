package com.example.voyproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME_FOODLIST = "foodTable";
    private static final String TABLE_NAME_CATEGORYLIST = "categoryTable";

    private static final String ID_FOOD= "_id";
    private static final String FOOD_NAME = "name";
    private static final String FOOD_KCAL = "kcal";
    private static final String FOOD_PROTEIN = "protein";
    private static final String FOOD_FAT = "fat";
    private static final String FOOD_CARBO = "carbo";
    private static final String FOOD_GRAMM = "gramm";

    private static final String FOOD_DATE = "gramm";
    private static final String FOOD_CATEGORY= "gramm";

    private static final String DB_NAME = "main_database.db";
    private static final int DB_VERSION = 1;
    private Context context;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME_FOODLIST + " (" + ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FOOD_NAME +  " TEXT, " + FOOD_KCAL + " INTEGER, " +
                FOOD_PROTEIN + " INTEGER, " + FOOD_FAT + " INTEGER, " +
                FOOD_CARBO + " INTEGER, " + FOOD_GRAMM + " INTEGER)";
        String query2 = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME_CATEGORYLIST + " (" + ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FOOD_NAME +  " TEXT, " + FOOD_KCAL + " INTEGER, " +
                FOOD_PROTEIN + " INTEGER, " + FOOD_FAT + " INTEGER, " +
                FOOD_CARBO + " INTEGER, " + FOOD_GRAMM + " INTEGER, " +
                FOOD_CATEGORY + " TEXT, " + FOOD_DATE + " DATE)";
        db.execSQL(query1);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FOODLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORYLIST);
        onCreate(db);
    }

    public void addFood(String name, String kcal, String protein, String fat, String carbo, String gramm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FOOD_NAME, name);
        cv.put(FOOD_KCAL, kcal);
        cv.put(FOOD_PROTEIN, protein);
        cv.put(FOOD_FAT, fat);
        cv.put(FOOD_CARBO, carbo);
        cv.put(FOOD_GRAMM, gramm);
        long result = db.insert(TABLE_NAME_FOODLIST, null, cv);
        if(result == -1)
            Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Блюдо добавлено", Toast.LENGTH_LONG).show();
    }

    public void moveFood(String name, String kcal, String protein, String fat, String carbo, String gramm, String category, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FOOD_NAME, name);
        cv.put(FOOD_KCAL, kcal);
        cv.put(FOOD_PROTEIN, protein);
        cv.put(FOOD_FAT, fat);
        cv.put(FOOD_CARBO, carbo);
        cv.put(FOOD_GRAMM, gramm);
        cv.put(FOOD_CATEGORY, category);
        cv.put(FOOD_DATE, date);
        long result = db.insert(TABLE_NAME_CATEGORYLIST, null, cv);
        if(result == -1)
            Toast.makeText(context, "Ошибка", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Блюдо добавлено", Toast.LENGTH_LONG).show();
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_FOODLIST;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
