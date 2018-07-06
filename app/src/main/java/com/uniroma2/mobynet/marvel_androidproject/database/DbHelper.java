package com.uniroma2.mobynet.marvel_androidproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {

    /* Attributi */
    public static final String TABLE_CHARACTERS = "characters";
    public static final String COLUMN_ID_CHAR = "_id";
    public static final String COLUMN_NAME_CHAR = "name";

    public static final String TABLE_CREATORS = "creators";
    public static final String COLUMN_ID_CREAT = "_id";
    public static final String COLUMN_NAME_CREAT = "name";

    public static String DB_PATH = "";
    public static String DB_NAME = "marvel.db";
    public static final int DB_VERSION = 1;

    private SQLiteDatabase database;
    //private Context context;

    /* Costruttore */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTableCharQuery = "CREATE TABLE " + TABLE_CHARACTERS + " ( " + COLUMN_ID_CHAR +
                " integer PRIMARY KEY AUTO_INCREMENT NOT NULL, " + COLUMN_NAME_CHAR + " text NOT NULL ); ";
        db.execSQL(creatTableCharQuery);

        String creatTableCreatQuery = "create table " + TABLE_CREATORS + " (" + COLUMN_ID_CREAT +
                " integer primary key AUTO_INCREMENT not null, " + COLUMN_NAME_CREAT + " text not null ); ";
        db.execSQL(creatTableCreatQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /* Metodi */
    public void openDb() {
        database = getWritableDatabase();
    }
    public void closeDb() {
        database.close();
    }

    public Cursor getElement() {
        String[] columns = {COLUMN_ID_CHAR, COLUMN_NAME_CHAR};
        Cursor cursor = database.query("name", columns, null, null, null, null, null);
        return cursor;
    }

    public void fill() {
        // Leggi file, prendi il nome e mettilo
        String nome = "Hulk";
        ElementDB character = new ElementDB(1, nome);
        addElement(character);
    }

    public void addElement(ElementDB element){
        ContentValues values = element.toContentValues();
        database.insert("characters", null, values);

    }
}
