package com.uniroma2.mobynet.marvel_androidproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Tale classe implementa le funzioni per l'inizializzazione di un database, con tutte le
 * caratteristiche dello stesso
 */
public class DbHelper extends SQLiteOpenHelper {

    /* Attributi */
    static final String TABLE_CHARACTERS = "characters";
    private static final String COLUMN_ID_CHAR = "_id";
    static final String COLUMN_NAME_CHAR = "name";

    static final String TABLE_CREATORS = "creators";
    private static final String COLUMN_ID_CREAT = "_id";
    static final String COLUMN_NAME_CREAT = "name";

    private static String DB_NAME = "marvel.db";
    private static final int DB_VERSION = 1;

    /* Costruttore */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    /**
     * Questa funzione crea le tabelle relative ai creators e characters nel database db.
     *
     * @param db : database di tipo SQLiteDatabase nel quale creare le tabelle
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

    /**
     * Questa funzione verifica gli aggiornamenti fatti nel database, in base alla versione,
     * modificando opportunamente l'applicazione dell'utente (nel nostro caso e' superflua)
     *
     * @param db : database da aggiornare (se necessario);
     * @param oldVersion : versioni del database precedenti a quella corrente
     * @param newVersion : versione corrente del database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}