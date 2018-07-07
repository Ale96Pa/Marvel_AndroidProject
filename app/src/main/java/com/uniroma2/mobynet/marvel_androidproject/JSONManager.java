package com.uniroma2.mobynet.marvel_androidproject;

import android.support.v7.app.AppCompatActivity;

import com.uniroma2.mobynet.marvel_androidproject.model.Comic;
import com.uniroma2.mobynet.marvel_androidproject.model.ComicSummary;
import com.uniroma2.mobynet.marvel_androidproject.model.Event;
import com.uniroma2.mobynet.marvel_androidproject.model.EventSummary;
import com.uniroma2.mobynet.marvel_androidproject.model.Story;
import com.uniroma2.mobynet.marvel_androidproject.model.StorySummary;
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

    public String  get_json_character(String nameToSearch) {
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




        try{
            InputStream is = getAssets().open("characters.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            Character character=null;

            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("name").equals(nameToSearch)){

                    String name = obj.getString("name");
                    int id = obj.getInt("id");
                    String description = obj.getString("description");
                    String resourceURI = obj.getString("resourceURI");
                    String modified = obj.getString("modified");

                    JSONObject comics = obj.getJSONObject("comics");
                    Comic comic = get_json_comics(comics);
                    JSONObject thumbnails=obj.getJSONObject("thumbnail");
                    Thumbnail thumbnail=get_json_thumbnail( thumbnails);

                    JSONObject stories=obj.getJSONObject("stories");
                    Story story=get_json_story(stories);

                    JSONObject events=obj.getJSONObject("events");
                    Event event=get_json_event(events);



                    character = new Character(id, name,description,modified,resourceURI,ArrayList< Url > urls,
                            thumbnail, Comic comics, Story stories, Event events);

                }
            }


        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return nome;
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




    public Story  get_json_story(JSONObject stories) {
        Story story=null;
        try{
            Integer available= stories.getInt("available");
            Integer returned = stories.getInt("returned");
            String collectionURI=stories.getString("collectionURI");
            JSONArray itemsArray = stories.getJSONArray("items");

            ArrayList<StorySummary> storySummaryArrayList= new ArrayList<>();

            for (int j = 0; j<itemsArray.length();j++){
                JSONObject items=itemsArray.getJSONObject(j);
                String resourceURI_item = items.getString("resourceURI");
                String name_item = items.getString("name");
                String type_item = items.getString("type");

                StorySummary storySummary = new StorySummary(resourceURI_item,name_item, type_item);

                storySummaryArrayList.add(storySummary);


            }


            story = new Story(available,returned,collectionURI,storySummaryArrayList);



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return story;

    }







    public Event  get_json_event(JSONObject events) {
        Event event=null;
        try{
            Integer available= events.getInt("available");
            Integer returned = events.getInt("returned");
            String collectionURI=events.getString("collectionURI");
            JSONArray itemsArray = events.getJSONArray("items");

            ArrayList<EventSummary> eventSummaryArrayList= new ArrayList<>();

            for (int j = 0; j<itemsArray.length();j++){
                JSONObject items=itemsArray.getJSONObject(j);
                String resourceURI_item = items.getString("resourceURI");
                String name_item = items.getString("name");

                EventSummary eventSummary = new EventSummary(resourceURI_item,name_item);

                eventSummaryArrayList.add(eventSummary);


            }


            event = new Event(available,returned,collectionURI,eventSummaryArrayList);



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return event;

    }






}
