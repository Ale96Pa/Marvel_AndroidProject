package com.uniroma2.mobynet.marvel_androidproject;

import android.support.v7.app.AppCompatActivity;

import com.uniroma2.mobynet.marvel_androidproject.model.Comic;
import com.uniroma2.mobynet.marvel_androidproject.model.ComicSummary;
import com.uniroma2.mobynet.marvel_androidproject.model.Creator;
import com.uniroma2.mobynet.marvel_androidproject.model.Character;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class JSONManager extends AppCompatActivity{


    public JSONManager(){

    }

    public void get_Character_By_Name(String name){

    }

    public Character  get_json_character(String nameToSearch) throws ParseException {
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

                    int id = obj.getInt("id");
                    String name = obj.getString("name");
                    String description = obj.getString("description");
                    String resourceURI = obj.getString("resourceURI");
                    String modified = obj.getString("modified");

                    JSONObject comics = obj.getJSONObject("comics");
                    Comic comic = get_json_comics( comics);

                    JSONObject thumbnails=obj.getJSONObject("thumbnail");
                    Thumbnail thumbnail=get_json_thumbnail( thumbnails);

                    JSONObject urls = obj.getJSONObject("urls");
                    ArrayList<Url> urlArrayList = get_json_urls(urls);

                    JSONObject events=obj.getJSONObject("event");
                    Event event=get_json_events( events);

                    JSONObject stories=obj.getJSONObject("story");
                    Story story=get_json_stories( stories);

                    character = new Character(id, name, description, modified, resourceURI, urlArrayList, thumbnail, comic, story, event);

                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return character;
    }








    public void get_Creator_By_Name(String name){

    }


    public Creator  get_json_creator(String nameToSearch) {
        String nome = null;
        String json = null;

        File file = new File("creator.json");

        try {

            file.createNewFile();

            RestRequest rs = new RestRequest("creator", nameToSearch);

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
        Creator creator=null;
        try{
            InputStream is = getAssets().open("creator.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);



            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("lastName").equals(nameToSearch)){

                    int id = obj.getInt("id");
                    String firstName = obj.getString("firstName");
                    String middleName = obj.getString("middleName");
                    String lastName = obj.getString("lastName");
                    String suffix = obj.getString("suffix");
                    String fullName = obj.getString("fullName");
                    String resourceURI = obj.getString("resourceURI");
                    String modified = obj.getString("modified");

                    JSONObject comics = obj.getJSONObject("comics");
                    Comic comic = get_json_comics( comics);

                    JSONObject thumbnails=obj.getJSONObject("thumbnail");
                    Thumbnail thumbnail=get_json_thumbnail( thumbnails);

                    JSONObject urls = obj.getJSONObject("urls");
                    ArrayList<Url> urlArrayList = get_json_urls(urls);

                    JSONObject events=obj.getJSONObject("event");
                    Event event=get_json_events( events);

                    JSONObject stories=obj.getJSONObject("story");
                    Story story=get_json_stories( stories);

                    creator = new Creator(id, firstName, middleName, lastName, suffix, fullName, modified,resourceURI,
                            urlArrayList, thumbnail, comic, story, event);

                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return creator;
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



    public Event  get_json_events( JSONObject events) {
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




    public Story  get_json_stories( JSONObject stories) {
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
                String type_item = items.getString("name");

                StorySummary storySummary = new StorySummary(resourceURI_item,name_item, type_item);

                storySummaryArrayList.add(storySummary);


            }


            story = new Story(available,returned,collectionURI,storySummaryArrayList);



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return story;

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
