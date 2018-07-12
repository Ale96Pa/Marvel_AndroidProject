package com.uniroma2.mobynet.marvel_androidproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CHAR;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CREAT;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CHARACTERS;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CREATORS;

/**
 * Tale classe implementa i metodi funzionali per l'utilizzo del database, quindi tutta la logica
 * dell'inserimento e della lettura dei dati
 */
public class DbManager {

    /* Attributi*/
    private Context context;
    private final String all = "0"; //Stringa usata per fare un inserimento e ricerca TOTALE

    /* Costruttore */
    public DbManager(Context context){
        this.context = context;
    }

    /**
     * Questa funzione riempie la tabella dei characters inserendo le righe lette dal file
     * characters.txt
     *
     * @param rowName : e' una riga del file characters.txt, ossia il nome di un personaggio;
     * @param db : database con la tabella da riempire;
     */
    private void addRowCharacter(String rowName, SQLiteDatabase db) {

        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CHAR, rowName);

        try {
            db.insert(TABLE_CHARACTERS, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR characters", e.toString());
            e.printStackTrace();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }
    /**
     * Questa funzione riempie la tabella dei creators inserendo le righe lette dal file
     * creators.txt
     *
     * @param rowName : e' una riga del file creators.txt, ossia cognome e nome di un creatore;
     * @param db : database con la tabella da riempire;
     */
    private void addRowCreator(String rowName, SQLiteDatabase db) {

        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CREAT, rowName);

        try {
            db.insert(TABLE_CREATORS, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR creators", e.toString());
            e.printStackTrace();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }



    /**
     * Questa funzione legge riga per riga il file characters.txt nella directory assets e,
     * per ciascuna riga letta, invoca la funzione addRowCharacter per inserire i valori.
     *
     * @param db : database nel quale inserire i valori letti dal file;
     * @param userSearch : contenuto della ricerca effettuata dall'utente (se "0" inserisce tutto);
     * @throws IOException : eccezione generata durante una scorretta apertura o lettura del file;
     */
    public void insertDataCharacters(SQLiteDatabase db, String userSearch)  throws IOException {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = context.getAssets().open("characters.txt");
            reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if(line != null) {
                    if(Objects.equals(userSearch, all)){
                        addRowCharacter(line, db);
                    } else {
                        userSearch.toLowerCase();
                        String user_search_upper = userSearch.substring(0, 1).toUpperCase() +
                                userSearch.substring(1, userSearch.length()).toLowerCase();
                        if (line.contains(userSearch) || line.contains(user_search_upper)) {
                            addRowCharacter(line, db);
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
    /**
     * Questa funzione legge riga per riga il file creators.txt nella directory assets e
     * per ciascuna riga letta, invoca la funzione addRowCreator per inserire i valori.
     *
     * @param db : database nel quale inserire i valori letti dal file;
     * @param userSearch : contenuto della ricerca effettuata dall'utente (se "0" inserisce TUTTO);
     * @throws IOException : eccezione generata durante una scorretta apertura o lettura del file;
     */
    public void insertDataCreators(SQLiteDatabase db, String userSearch)  throws IOException {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = context.getAssets().open("creators.txt");
            reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if(line != null) {
                    if(Objects.equals(userSearch, all)){
                        addRowCreator(line, db);
                    } else {
                        userSearch.toLowerCase();
                        String user_search_upper = userSearch.substring(0, 1).toUpperCase() +
                                userSearch.substring(1, userSearch.length()).toLowerCase();
                        if (line.contains(userSearch) || line.contains(user_search_upper)) {
                            addRowCreator(line, db);
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

}
