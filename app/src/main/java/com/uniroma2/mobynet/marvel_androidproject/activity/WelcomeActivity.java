package com.uniroma2.mobynet.marvel_androidproject.activity;


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
import java.io.InputStreamReader;

import com.uniroma2.mobynet.marvel_androidproject.R;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbManager.addRowCharacter;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnStart;
    SQLiteDatabase db;

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
        String SDcardPath = Environment.getExternalStorageDirectory().getPath();
        String dbPath = SDcardPath + "/" + "marvel";
        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            // Funzione che prende dati dal file e li inserisce nel database (tutte e due le tabelle)
            insertDataCharacters();

            db.close();
        } catch (SQLException e1) {
            Log.e("DB ERROR", e1.toString());
        } catch(IOException e2) {
            e2.printStackTrace();
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

    public void insertDataCharacters()  throws IOException {

        //File file = null;
    //    try {
            //FileInputStream fis = new FileInputStream(file);
            try {
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(getAssets().open("characters.txt")));
                String line;
                while ((line = br.readLine()) != null) {

                    System.out.println(line);
                    addRowCharacter(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                /*try {
                    //fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
/*        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}