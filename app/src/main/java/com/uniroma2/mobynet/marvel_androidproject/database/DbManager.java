package com.uniroma2.mobynet.marvel_androidproject.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CHAR;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CREAT;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CHARACTERS;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CREATORS;

public class DbManager {

    public static void addRowCharacter(String rowName, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CHAR, rowName);
        try {
            //SQLiteDatabase db = DbHelper.getDatabase();
            database.insert(TABLE_CHARACTERS, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }
    public void addRowCreator(String rowName, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CREAT, rowName);
        try {
            //SQLiteDatabase db = DbHelper.getDatabase();
            database.insert(TABLE_CREATORS, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }
}
