package com.uniroma2.mobynet.marvel_androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class SearchCreatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_creator);
    }

    public String  get_json(){
        String nome = null;
        String json = null;
        try{
            InputStream is = getAssets().open("1009144.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            //json = new String(buffer);
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);


            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getInt("id")==1009144){
                    nome=obj.getString("name");
                }
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nome;
    }
}
