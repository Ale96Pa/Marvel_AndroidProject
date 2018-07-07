package com.uniroma2.mobynet.marvel_androidproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    /* Attributi */
    public static final String TABLE_CHARACTERS = "characters";
    public static final String COLUMN_ID_CHAR = "_id";
    public static final String COLUMN_NAME_CHAR = "name";

    public static final String TABLE_CREATORS = "creators";
    public static final String COLUMN_ID_CREAT = "_id";
    public static final String COLUMN_NAME_CREAT = "name";

    public static String DB_NAME = "marvel.db";
    public static final int DB_VERSION = 1;

    /* Costruttore */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /* Metodi */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTableCharQuery = "CREATE TABLE " + TABLE_CHARACTERS + " ( " + COLUMN_ID_CHAR +
                " integer PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_NAME_CHAR + " text ); ";
        db.execSQL(creatTableCharQuery);

        String creatTableCreatQuery = "CREATE TABLE " + TABLE_CREATORS + " ( " + COLUMN_ID_CREAT +
                " integer PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_NAME_CREAT + " text ); ";
        db.execSQL(creatTableCreatQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
