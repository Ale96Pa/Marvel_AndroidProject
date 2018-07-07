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
import com.uniroma2.mobynet.marvel_androidproject.database.DbManager;

import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CHAR;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.COLUMN_NAME_CREAT;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CHARACTERS;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbHelper.TABLE_CREATORS;
import static com.uniroma2.mobynet.marvel_androidproject.database.DbManager.*;

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
        DBhelper = new DbHelper(this);
        db = DBhelper.getWritableDatabase();
        final DbManager manager = new DbManager(this);
        String user_search = "deodati";
/*
        try {
            manager.insertDataCharacters(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


}