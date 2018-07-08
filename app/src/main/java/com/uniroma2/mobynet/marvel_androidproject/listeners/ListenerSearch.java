package com.uniroma2.mobynet.marvel_androidproject.listeners;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.database.DbHelper;
import com.uniroma2.mobynet.marvel_androidproject.database.DbManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ListenerSearch implements View.OnClickListener {
    private String user_search;
    private EditText etSearch;
    private int type;
    private ListView lvElements;
    Context context;

    private String query;
    ArrayList<String> queryResults;

    public ListenerSearch(String user_search, EditText etSearch, int type, ListView lvElements, Context context) {
        this.user_search = user_search;
        this.etSearch = etSearch;
        this.type = type;
        this.lvElements = lvElements;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        user_search = String.valueOf(etSearch.getText());

        DbHelper DBhelper = new DbHelper(context);
        SQLiteDatabase db = DBhelper.getWritableDatabase();
        final DbManager manager = new DbManager(context);

        if(user_search.contains("'")){
            user_search = user_search.replace('\'', ' ');
            Toast.makeText(context, R.string.apix, Toast.LENGTH_LONG).show();
        }

        if(type == 1){
            try {
                manager.insertDataCharacters(db, user_search);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String user_search_upper = user_search.substring(0, 1).toUpperCase() +
                    user_search.substring(1, user_search.length()).toLowerCase();
            query = "SELECT DISTINCT * FROM characters WHERE name like '%" + user_search + "%' or name like '%" + user_search_upper + "%';";

        } else {
            try {
                manager.insertDataCreators(db, user_search);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String user_search_upper = user_search.substring(0,1).toUpperCase() +
                    user_search.substring(1, user_search.length()).toLowerCase();
            query = "SELECT DISTINCT * FROM creators WHERE name like '%"+user_search+"%' or name like '%"+user_search_upper+"%';";
        }

        Cursor cursor = db.rawQuery(query, new String[]{});
        queryResults = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                queryResults.add(name);
            } while(cursor.moveToNext());
        }
        cursor.close();
        List<String> allSearchedElements = new ArrayList<>(new LinkedHashSet<>(queryResults));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, allSearchedElements);
        lvElements.setAdapter(adapter);
        ListenerSearchItem listenerSearchItem = new ListenerSearchItem(context, type);
        lvElements.setOnItemClickListener(listenerSearchItem);

    }

}

