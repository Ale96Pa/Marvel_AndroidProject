package com.uniroma2.mobynet.marvel_androidproject.listeners;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListenerSearch implements View.OnClickListener {
    private String user_search;
    private EditText etSearch;
    private int type;
    private SQLiteDatabase db;
    private ListView lvElements;
    Context context;

    private String query;
    ArrayList<String> allSearchedElements;

    public ListenerSearch(String user_search, EditText etSearch, int type, SQLiteDatabase db,
                          ListView lvElements, Context context) {
        this.user_search = user_search;
        this.etSearch = etSearch;
        this.type = type;
        this.db = db;
        this.lvElements = lvElements;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        user_search = String.valueOf(etSearch.getText());
        if(type == 1){
            query = "SELECT DISTINCT * FROM characters WHERE name like '%"+user_search+"%';";
        } else {
            query = "SELECT DISTINCT * FROM creators WHERE name like '%"+user_search+"%';";
        }

        Cursor cursor = db.rawQuery(query, new String[]{});
        allSearchedElements = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                allSearchedElements.add(name);
            } while(cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, allSearchedElements);
        lvElements.setAdapter(adapater);
        ListenerSearchItem listenerSearchItem = new ListenerSearchItem(context, type);
        lvElements.setOnItemClickListener(listenerSearchItem);
    }

}

