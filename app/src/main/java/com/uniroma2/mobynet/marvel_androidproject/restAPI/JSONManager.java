package com.uniroma2.mobynet.marvel_androidproject.restAPI;

import android.content.Context;

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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class JSONManager{
    private Context context;

    public JSONManager(Context context){
        this.context = context;
    }


    public String loadJSONFromAsset(Context c, String jsonName) {
        String json = null;
        try {
            InputStream is = c.getAssets().open(jsonName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public Character  get_json_character(String nameToSearch) {

        String res=null;


            RestRequest rs = new RestRequest("characters", nameToSearch);

            try {

                rs.sendGet();
                res = rs.getResult();
                /*
                System.out.println("****** RESULT JSON *******");
                System.out.println(res);
                */

            } catch (Exception e) {
                e.printStackTrace();
            }

        Character character=null;
        try{

            JSONObject obj = new JSONObject(res);


                    JSONObject charObj = obj.getJSONObject("data").getJSONArray("results").getJSONObject(0);

                    int id;
                    String name = null, description = null, resourceURI = null, modified = null;
                    ArrayList<Url> urlArrayList = new ArrayList<>();
                    Event event = new Event();
                    Story story = new Story();
                    Comic comic = new Comic(){} ;
                    Thumbnail thumbnail = new Thumbnail();

                    id = charObj.getInt("id");

                    if(charObj.getString("name")!=null){
                        name = charObj.getString("name");
                    }
                    if(charObj.getString("description")!=null){
                        description = charObj.getString("description");
                    }
                    if(charObj.getString("resourceURI")!=null){
                        resourceURI = charObj.getString("resourceURI");
                    }
                    if(charObj.getString("modified")!=null){
                        modified = charObj.getString("modified");
                    }

                    System.out.println("id:"+id+" name:"+name+" description:"+description);

                    if(charObj.getString("comics")!=null){
                        JSONObject comics = charObj.getJSONObject("comics");
                        comic = get_json_comics( comics);
                    }


                    if(charObj.getString("thumbnail")!=null){
                        JSONObject thumbnails=charObj.getJSONObject("thumbnail");
                        thumbnail=get_json_thumbnail( thumbnails);
                    }



                    if(charObj.getString("urls")!=null){
                        JSONArray urls = charObj.getJSONArray("urls");
                        urlArrayList = get_json_urls(urls);
                    }


                    if(charObj.getString("events")!=null){
                        JSONObject events=charObj.getJSONObject("events");
                        event=get_json_events( events);
                    }



                    if(charObj.getString("stories")!=null){
                        JSONObject stories=charObj.getJSONObject("stories");
                        story=get_json_stories( stories);
                    }

                    character = new Character(id, name, description, modified, resourceURI, urlArrayList, thumbnail, comic, story, event);

            } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return character;
    }

    public void get_Creator_By_Name(String name){

    }


    public Creator  get_json_creator(String nameToSearch) {


        String res = null;
        RestRequest rs = new RestRequest("creators", nameToSearch);

            try {

                rs.sendGet();
                res = rs.getResult();
                /*
                System.out.println("****** RESULT JSON *******");
                System.out.println(res);
                */


            } catch (Exception e) {
                e.printStackTrace();
            }

        Creator creator=null;

        try{

            JSONObject obj = new JSONObject(res);



            JSONObject charObj = obj.getJSONObject("data").getJSONArray("results").getJSONObject(0);

            String firstName = null, middleName = null, lastName = null, fullName=null, suffix=null, resourceURI=null, modified=null;
            int id;
            ArrayList<Url> urlArrayList = new ArrayList<>();
            Event event = new Event();
            Story story = new Story();
            Comic comic = new Comic(){} ;
            Thumbnail thumbnail = new Thumbnail();

            id = charObj.getInt("id");

            if(charObj.getString("firstName")!=null){
                firstName = charObj.getString("firstName");
            }
            if(charObj.getString("middleName")!=null){
                middleName = charObj.getString("middleName");
            }
            if(charObj.getString("lastName")!=null){
                lastName = charObj.getString("lastName");
            }
            if(charObj.getString("fullName")!=null){
                fullName = charObj.getString("fullName");
            }

            if(charObj.getString("suffix")!=null){
                suffix = charObj.getString("suffix");
            }
            if(charObj.getString("resourceURI")!=null){
                resourceURI = charObj.getString("resourceURI");
            }
            if(charObj.getString("modified")!=null){
                modified = charObj.getString("modified");
            }

            //System.out.println("id:"+id+" name:"+firstName);

            if(charObj.getString("comics")!=null){
                JSONObject comics = charObj.getJSONObject("comics");
                comic = get_json_comics( comics);
            }


            if(charObj.getString("thumbnail")!=null){
                JSONObject thumbnails=charObj.getJSONObject("thumbnail");
                thumbnail=get_json_thumbnail( thumbnails);
            }



            if(charObj.getString("urls")!=null){
                JSONArray urls = charObj.getJSONArray("urls");
                urlArrayList = get_json_urls(urls);
            }


            if(charObj.getString("events")!=null){
                JSONObject events=charObj.getJSONObject("events");
                event=get_json_events( events);
            }



            if(charObj.getString("stories")!=null){
                JSONObject stories=charObj.getJSONObject("stories");
                story=get_json_stories( stories);
            }


            creator = new Creator( id, firstName, middleName, lastName, suffix, fullName, modified,
                    resourceURI, urlArrayList, thumbnail, comic, story, event);

        } catch (JSONException e1) {
            e1.printStackTrace();
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




    public ArrayList<Url>  get_json_urls( JSONArray urls) {
        ArrayList<Url> urlArrayList= new ArrayList<>();

        try{


            for (int j = 0; j<urls.length();j++){

                Url singleUrl;

                String type = urls.getJSONObject(j).getString("type");
                String url = urls.getJSONObject(j).getString("url");

                singleUrl = new Url(type,url);

                if(singleUrl!=null){ urlArrayList.add(singleUrl);}


            }



        } catch (JSONException e) {
            e.printStackTrace();
        }



        return urlArrayList;

    }


}
