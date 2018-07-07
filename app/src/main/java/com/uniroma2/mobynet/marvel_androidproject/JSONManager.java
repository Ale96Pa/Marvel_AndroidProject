package com.uniroma2.mobynet.marvel_androidproject;

import android.support.v7.app.AppCompatActivity;

import com.uniroma2.mobynet.marvel_androidproject.model.Comic;
import com.uniroma2.mobynet.marvel_androidproject.model.ComicSummary;
import com.uniroma2.mobynet.marvel_androidproject.model.Event;
import com.uniroma2.mobynet.marvel_androidproject.model.Story;
import com.uniroma2.mobynet.marvel_androidproject.model.Thumbnail;
import com.uniroma2.mobynet.marvel_androidproject.model.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;


public class JSONManager extends AppCompatActivity{

    public JSONManager(){

    }

    public void get_Character_By_Name(String name){

    }

    public Character  get_json_character(String nameToSearch) {
        String nome = null;
        String json = null;

        File file = new File("characters.json");

        try {

            file.createNewFile();

            RestRequest rs = new RestRequest("character", nameToSearch);

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
        Character character=null;
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
                if (obj.getString("name").equals(nameToSearch)){

                    String name = obj.getString("name");
                    int id = obj.getInt("id");
                    String description = obj.getString("description");
                    String resourceURI = obj.getString("resourceURI");
                    String modified = obj.getString("modified");

                    JSONObject comics = obj.getJSONObject("comics");
                    Comic comic = get_json_comics( comics);
                    JSONObject thumbnails=obj.getJSONObject("thumbnail");
                    Thumbnail thumbnail=get_json_thumbnail( thumbnails);
                    JSONObject urls = obj.getJSONObject("urls");
                    ArrayList<Url> urlArrayList = get_json_urls(urls);


                    character = new Character(id, name,description,modified,resourceURI,ArrayList< Url > urls,
                            thumbnail, comic, Story stories, Event events);

                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return character;
    }

    public Comic  get_json_comics( JSONObject comics) {
        Comic comic=null;
        try{
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


            comic = new Comic(available,returned,collectionURI,comicSummaryArrayList);



                } catch (JSONException e) {
            e.printStackTrace();
        }



        return comic;

    }

    public Thumbnail  get_json_thumbnail( JSONObject thumbnails) {
        Thumbnail thumbnail=null;
        try{
            String path= thumbnails.getString("path");
            String extension= thumbnails.getString("extension");


            thumbnail = new Thumbnail(path,extension);



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return thumbnail;

    }

    public ArrayList<Url>  get_json_urls( JSONObject urls) {
        ArrayList<Url> urlArrayList=null;
        try{




            for (int j = 0; j<urls.length();j++){
                String type= urls.getString("type");
                String url = urls.getString("url");

                Url singleUrl = new Url(type,url);


                urlArrayList.add(singleUrl);


            }



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return urlArrayList;

    }
}
