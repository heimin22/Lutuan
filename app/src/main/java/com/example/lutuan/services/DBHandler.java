package com.example.lutuan.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DISH_DB";
    private static final String ID_COL = "ID";
    private static final String DISH_COL = "DISH";
    private static final String INGREDIENTS_COL = "INGREDIENTS";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void addDish(String dish, String ingredients) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DISH_COL, dish);
        values.put(INGREDIENTS_COL, ingredients);
        db.insert(DB_NAME, null, values);
    }

    public String formatIngredients(String[] checkedValues) {
        StringBuilder formattedValues = new StringBuilder();
        int lastIndex = checkedValues.length;
        int currentIndex = 0;
        for (String checkedValue : checkedValues) {
            currentIndex++;
            if(currentIndex == lastIndex) {
                formattedValues.append(checkedValue);
            } else {
                formattedValues.append(checkedValue).append(", ");
            }
        }
        return formattedValues.toString();
    }

    public String getDishByIngredients(String ingredients){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DB_NAME + " WHERE " + INGREDIENTS_COL + " = '" + ingredients + "'";
        String dish = db.rawQuery(query, null).toString();
        if(dish.isEmpty()) return "No Dish Found";
        return dish;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DB_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DISH_COL + " TEXT,"
                + INGREDIENTS_COL + " TEXT)";
        sqLiteDatabase.execSQL(query);

        ContentValues adobo = new ContentValues();
        adobo.put(DISH_COL, "adobo");
        adobo.put(INGREDIENTS_COL, "bay leaf, chicken, garlic, soy sauce, vinegar");
        sqLiteDatabase.insert(DB_NAME, null, adobo);

        ContentValues sinigang = new ContentValues();
        sinigang.put(DISH_COL, "sinigang");
        sinigang.put(INGREDIENTS_COL, "onion, pork, radish, tamarind, tomato");
        sqLiteDatabase.insert(DB_NAME, null, sinigang);

        ContentValues lechon_kawali = new ContentValues();
        lechon_kawali.put(DISH_COL, "lechon kawali");
        lechon_kawali.put(INGREDIENTS_COL, "pork belly, salt, water");
        sqLiteDatabase.insert(DB_NAME, null, lechon_kawali);

        ContentValues kare_kare = new ContentValues();
        kare_kare.put(DISH_COL, "kare-kare");
        kare_kare.put(INGREDIENTS_COL, "eggplant, oxtail, peanut butter, string beans");
        sqLiteDatabase.insert(DB_NAME, null, kare_kare);

        ContentValues bicol_express = new ContentValues();
        bicol_express.put(DISH_COL, "bicol express");
        bicol_express.put(INGREDIENTS_COL, "chili, coconut milk, pork, shrimp paste");
        sqLiteDatabase.insert(DB_NAME, null, bicol_express);

        ContentValues tapsilog = new ContentValues();
        tapsilog.put(DISH_COL, "tapsilog");
        tapsilog.put(INGREDIENTS_COL, "beef, egg, garlic, rice");
        sqLiteDatabase.insert(DB_NAME, null, tapsilog);

        ContentValues lumpiang_shanghai = new ContentValues();
        lumpiang_shanghai.put(DISH_COL, "lumpiang shanghai");
        lumpiang_shanghai.put(INGREDIENTS_COL, "carrot, garlic, ground pork, onion, wrapper");
        sqLiteDatabase.insert(DB_NAME, null, lumpiang_shanghai);

        ContentValues tinola = new ContentValues();
        tinola.put(DISH_COL, "tinola");
        tinola.put(INGREDIENTS_COL, "chicken, chayote, ginger, onion");
        sqLiteDatabase.insert(DB_NAME, null, tinola);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }
}
