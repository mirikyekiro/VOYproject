package com.example.voyproject.Database;

import static java.lang.Integer.valueOf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;

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

    private static final String FOOD_GROUP = "groupFood";

    private static final String FOOD_DATE = "date";
    private static final String FOOD_CATEGORY= "category";

    private static String DB_PATH;
    private static final String DB_NAME = "main_database.db";
    private static final int DB_VERSION = 1;
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
        DB_PATH = "/data/data/com.example.voyproject/databases/main_database.db";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FOODLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORYLIST);
        onCreate(db);
    }

    public void deleteTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_CATEGORYLIST);
    }

    public void addFood(String name, String kcal, String protein, String fat, String carbo, String gramm, String groupFood){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FOOD_NAME, name);
        cv.put(FOOD_KCAL, kcal);
        cv.put(FOOD_PROTEIN, protein);
        cv.put(FOOD_FAT, fat);
        cv.put(FOOD_CARBO, carbo);
        cv.put(FOOD_GRAMM, gramm);
        cv.put(FOOD_GROUP, groupFood);
        long result = db.insert(TABLE_NAME_FOODLIST, null, cv);
        if(result == -1)
            Toast.makeText(context, "Ошибка при добавлении", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Успешно добавлено", Toast.LENGTH_LONG).show();
    }

    public void deleteOneRow(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_FOODLIST, "_id=?", new String[]{id});
        if(result == -1)
            Toast.makeText(context, "Ошибка при удалении", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Успешно удалено", Toast.LENGTH_LONG).show();
    }

    public void deleteOneRowInProfile(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_CATEGORYLIST, "_id=?", new String[]{id});
        if(result == -1)
            Toast.makeText(context, "Ошибка при удалении", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Успешно удалено", Toast.LENGTH_LONG).show();
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
            Toast.makeText(context, "Ошибка при добавлении", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Успешно добавлено", Toast.LENGTH_LONG).show();
    }

    public Cursor readAllData(String group){
        String query = "SELECT * FROM " + TABLE_NAME_FOODLIST + " WHERE "+FOOD_GROUP+" LIKE '"+group+"' ORDER BY name";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllDataCATEGORYLIST(String textMeal, String date){
        String query = "SELECT * FROM " + TABLE_NAME_CATEGORYLIST +
                " WHERE " + FOOD_CATEGORY + " LIKE '" + textMeal + "' AND date LIKE '"+date+"' ORDER BY name";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public float getSum(String str, String category, String date){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        if(category == "")
            cursor = db.query (TABLE_NAME_CATEGORYLIST, new String[] {"SUM("+str+"), date"}, "date = ?", new String[] {date}, null, null, null);
        else
            cursor = db.query (TABLE_NAME_CATEGORYLIST, new String[] {"SUM("+str+"), category, date"}, "category = '"+category+"' AND date = '"+date+"'", null, null, null, null);

        float sum, index;
        if(cursor.moveToFirst()) {
            index = cursor.getFloat(0);
            sum = Float.parseFloat(String.valueOf(index));
        }
        else sum = 0;

        db.close();
        return sum;
    }

    public String getNameFood(String category, String date)
    {
        String text = "";

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT group_concat(name, ', ') AS nameFood FROM " + TABLE_NAME_CATEGORYLIST +
                    " WHERE " + FOOD_CATEGORY + " LIKE '" + category + "' AND date LIKE '"+date+"' ORDER BY name";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
            text = cursor.getString(0);
        else text = "Пусто";

        db.close();
        return text;
    }

    public void create_db(){
        File file = new File(DB_PATH);
        if (!file.exists()) {
            try(InputStream myInput = context.getAssets().open(DB_NAME);
                OutputStream myOutput = new FileOutputStream(DB_PATH)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
