package com.uniroma2.mobynet.marvel_androidproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CHAR;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CREAT;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CHARACTERS;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CREATORS;

public class DbManager extends AppCompatActivity {

    private Context context;

    /* Costruttore */
    public DbManager(Context context){
        this.context = context;
    }

    /* Metodi */
    public void addRowCharacter(String rowName, SQLiteDatabase db) {

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
    public void addRowCreator(String rowName, SQLiteDatabase db) {

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
                    userSearch.toLowerCase();
                    String user_search_upper = userSearch.substring(0,1).toUpperCase() +
                            userSearch.substring(1, userSearch.length()).toLowerCase();
                    if (line.contains(userSearch) || line.contains(user_search_upper)) {
                        addRowCharacter(line, db);
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
                    userSearch.toLowerCase();
                    String user_search_upper = userSearch.substring(0,1).toUpperCase() +
                            userSearch.substring(1, userSearch.length()).toLowerCase();
                    if (line.contains(userSearch) || line.contains(user_search_upper)) {
                        addRowCreator(line, db);
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
