package com.example.voyproject.Database;

import java.util.Date;

public class FoodConstants {
    public static final String TABLE_NAME_FOODLIST = "foodTable";
    public static final String TABLE_NAME_FOODPROFILE = "foodProfileTable";

    public static final String ID_FOOD= "_id";
    public static final String FOOD_NAME = "name";
    public static final String FOOD_KCAL = "kcal";
    public static final String FOOD_PROTEIN = "protein";
    public static final String FOOD_FAT = "fat";
    public static final String FOOD_CARBO = "carbo";
    public static final String FOOD_GRAMM = "gramm";
    public static final String FOODPROFILE_DATE = "date";

    public static final String DB_NAME = "main_db.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_FOODLIST_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME_FOODPROFILE + " (" + ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FOOD_NAME +  " TEXT, " + FOOD_KCAL + " INTEGER, " +
            FOOD_PROTEIN + " INTEGER, " + FOOD_FAT + " INTEGER, " +
            FOOD_CARBO + " INTEGER, " + FOOD_GRAMM + " INTEGER, " +
            FOODPROFILE_DATE + " TEXT);";
    public static final String DROP_TABLE_FOODLIST = "DROP TABLE IF EXISTS " + TABLE_NAME_FOODLIST;


    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME_FOODPROFILE + " (" + ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FOOD_NAME +  " TEXT, " + FOOD_KCAL + " INTEGER, " +
            FOOD_PROTEIN + " INTEGER, " + FOOD_FAT + " INTEGER, " +
            FOOD_CARBO + " INTEGER, " + FOOD_GRAMM + " INTEGER, " +
            FOODPROFILE_DATE + " TEXT);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_FOODPROFILE;
}
