package com.uniroma2.mobynet.marvel_androidproject.activity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.database.DbHelper;

import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CHAR;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CHARACTERS;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbManager.addRowCharacter;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnStart;
    SQLiteDatabase db;
    static DbHelper DBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
/*
        RestRequest rs = new RestRequest("characters");
        try {
            rs.sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        //DbHelper dbHelper = new DbHelper(this);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
/*        helper = new DbHelper(this);
        helper.openDb();
        System.out.println("**************** ESITO !!!!!!!!!!!" + db.isOpen());

        String SDcardPath = Environment.getExternalStorageDirectory().getPath();
        String dbPath = SDcardPath + "/" + "marvel";
        try {
            //db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            // Funzione che prende dati dal file e li inserisce nel database (tutte e due le tabelle)
            insertDataCharacters();

            db.close();
        } catch (SQLException e1) {
            Log.e("DB ERROR", e1.toString());
        } catch(IOException e2) {
            e2.printStackTrace();
        }
*/
        try {
            insertDataCharacters();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void addRowCharacter(String rowName) {
        DBhelper = new DbHelper(this);
        SQLiteDatabase db = DBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CHAR, rowName);

        try {
            db.insert(TABLE_CHARACTERS, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void insertDataCharacters()  throws IOException {
        try {
            InputStream is = getAssets().open("characters.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                addRowCharacter(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}