package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.listeners.ListenerSearch;
import com.uniroma2.mobynet.marvel_androidproject.listeners.ListenerSearchItem;
import com.uniroma2.mobynet.marvel_androidproject.restAPI.RestRequest;
import com.uniroma2.mobynet.marvel_androidproject.database.DbHelper;
import com.uniroma2.mobynet.marvel_androidproject.database.DbManager;
import com.uniroma2.mobynet.marvel_androidproject.model.ComicSummary;

public class SearchElementActivity extends AppCompatActivity {

    private EditText etSearch;
    private ListView lvElements;
    private TextView tvElement;
    private Button btnSearch;
    private String user_search;

    SQLiteDatabase db;
    static DbHelper DBhelper;
    Context context;

    int type; // 1 if characters, 2 if creator
    ArrayList<String> allSearchedElements = new ArrayList<>();

    public SearchElementActivity() {
        context = SearchElementActivity.this;
    }

    public Context getContext() {
        return this.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_element);

        etSearch = findViewById(R.id.etSearch);
        lvElements = findViewById(R.id.lvElementsSearched);
        tvElement = findViewById(R.id.tvInstructionSearch);
        btnSearch = findViewById(R.id.btnSearch);

        DBhelper = new DbHelper(this);
        db = DBhelper.getWritableDatabase();
        final DbManager manager = new DbManager(this);

        Bundle requested = getIntent().getExtras();
        if (requested != null)
            type = requested.getInt("type");
        if(type == 1) {
            tvElement.setText(R.string.searching_characters);
        }
        else {
            tvElement.setText(R.string.searching_creators);
        }

        context = SearchElementActivity.this;
        ListenerSearch listener = new ListenerSearch(user_search, etSearch, type, db, lvElements, context);
        btnSearch.setOnClickListener(listener);

    }




}