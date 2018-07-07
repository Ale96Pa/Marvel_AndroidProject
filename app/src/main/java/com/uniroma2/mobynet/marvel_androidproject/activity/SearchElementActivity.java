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
        return context;
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

        ListenerSearch listener = new ListenerSearch(user_search, etSearch, type, db, lvElements, context);
        btnSearch.setOnClickListener(listener);

/*
        Cursor cursor = db.rawQuery(query, new String[]{});
        allSearchedElements = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                allSearchedElements.add(name);
            } while(cursor.moveToNext());
        }
        cursor.close();
*/
        //allSearchedElements = listener.getAllSearchedElements();

/*
        ArrayAdapter<String> adapater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allSearchedElements);
        lvElements.setAdapter(adapater);
        ListenerSearchItem listenerSearchItem = new ListenerSearchItem(this, type);
        lvElements.setOnItemClickListener(listenerSearchItem);
 */

      //  String jsonString = get_json(etSearch.getText().toString());
    }



    public String  get_json(String nameToSearch) {
        String nome = null;
        String json = null;

        File file = new File("characters.json");

        try {

            file.createNewFile();

            RestRequest rs = new RestRequest("creators", nameToSearch);

            try {

                rs.sendGet();
                String res = rs.getResult();
                System.out.println("****** RESULT JSON *******");
                System.out.println(res);

                FileOutputStream fOut = new FileOutputStream(file);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

                myOutWriter.append(res);


            } catch (Exception e) {
                e.printStackTrace();
             }

        } catch (IOException e) {
            e.printStackTrace();
        }




        try{
            InputStream is = getAssets().open("characters.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("name")== nameToSearch){

                    String nomeC = obj.getString("name");
                    int id = obj.getInt("id");
                    String description = obj.getString("description");
                    String resourceURI = obj.getString("resourceURI");
                    String modified = obj.getString("modified");

                    JSONObject comics = obj.getJSONObject("comics");
                    Integer available= comics.getInt("available");
                    Integer returned = comics.getInt("returned");
                    String collectionURI=comics.getString("collectionURI");
                    JSONArray itemsArray = comics.getJSONArray("items");

                    ArrayList<ComicSummary> comicSummaryArrayList= new ArrayList<>();

                    for (int j = 0; j<itemsArray.length();j++){
                        JSONObject items=itemsArray.getJSONObject(j);
                        String resourceURI_item = items.getString("resourceURI");
                        String name_item = items.getString("name");

                        ComicSummary comicSummary = new ComicSummary(resourceURI_item,name_item);

                        comicSummaryArrayList.add(comicSummary);


                    }



                    //Character character = new Character(.....);

                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return nome;
    }
}