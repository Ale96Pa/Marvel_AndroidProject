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

    /**
     * Questo costruttore richiama il costruttore della superclasse SQLiteOpenHelper con il contesto
     * della classe che lo invoca, il nome del database marvel.db ed una versione del database.
     *
     * @param context : e' il contesto che viene passato dalla classe che invoca il costruttore
     */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /* Metodi */

    /**
     * Questa funzione crea le tabelle relative ai creators e characters con i rispettivi nomi
     * all'interno del database db.
     *
     * @param db : database di tipo SQLiteDatabase nel quale vengono create le tabelle
     */
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

