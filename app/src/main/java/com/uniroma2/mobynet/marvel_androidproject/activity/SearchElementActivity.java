package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
import com.uniroma2.mobynet.marvel_androidproject.RestRequest;
import com.uniroma2.mobynet.marvel_androidproject.database.DbHelper;
import com.uniroma2.mobynet.marvel_androidproject.model.ComicSummary;

import static com.uniroma2.mobynet.marvel_androidproject.activity.WelcomeActivity.DBhelper;

public class SearchElementActivity extends AppCompatActivity {

    private EditText etSearch;
    private ListView lvElements;
    final String querySearch = "SELECT * FROM creators WHERE name like '%deo%';";
    int type; // 1 if characters, 2 if creator
    ArrayList<String> allSearchedCharacters = new ArrayList<>();
    ArrayList<String> allSearchedCreators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_creator);
        etSearch = (EditText) findViewById(R.id.etSearch);
        lvElements = (ListView) findViewById(R.id.lvElementsSearched);

        Bundle requested = getIntent().getExtras();
        type = requested.getInt("type");
        System.out.println("*********YOU HAVE CLICKED: " + type);

        DBhelper = new DbHelper(this);
        SQLiteDatabase db = DBhelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(querySearch, new String[]{});
        allSearchedCharacters = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                allSearchedCharacters.add(name);
            } while(cursor.moveToNext());
        }
        cursor.close();



        ArrayAdapter<String> adapater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allSearchedCharacters);
        lvElements.setAdapter(adapater);

        lvElements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("**********CLICK ON ITEM: ********" + adapterView.getItemAtPosition(i));
                //Passare all'intent del personagio
            }
        });
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
                if (obj.getString("name")==nameToSearch){

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