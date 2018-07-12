package com.uniroma2.mobynet.marvel_androidproject.restAPI;

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
import java.util.ArrayList;

/**
 * La classe JSONManager costruisce istanze degli oggetti Character e Creator e dei loro attributi
 * sulla base della ricerca effettuata dall'utente
 *
 */
public class JSONManager{

    /* Costruttore */
    public JSONManager(){}

    /**
     * Questo metodo effettua una richiesta REST e restituisce un'istanza dell' oggetto Character
     * in base alla ricerca effettuata dall'utente
     *
     * @param nameToSearch: nome del character da ricercare
     * @return: modello del Character ricercato
     */
    public Character  get_json_character(String nameToSearch) {

        String res=null;
        // Esecuzione della richiesta
        RestRequest rs = new RestRequest("characters", nameToSearch);

        try {
            rs.sendGet();
            res = rs.getResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Character character=null;
        try{

            // Creazione di un JSONObject relativo al risultato della precedente ricerca
            JSONObject obj = new JSONObject(res);
            JSONObject charObj = obj.getJSONObject("data").getJSONArray("results").getJSONObject(0);

            int id;
            String name = null, description = null, resourceURI = null, modified = null;
            ArrayList<Url> urlArrayList = new ArrayList<>();
            Event event = new Event();
            Story story = new Story();
            Comic comic = new Comic(){} ;
            Thumbnail thumbnail = new Thumbnail();

            // Alcuni attributi di un Character sono opzionali, quindi si fanno controlli su valori nulli
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

    /**
     * Questo metodo effettua una richiesta REST e restituisce un'istanza dell' oggetto Creator
     * in base alla ricerca effettuata dall'utente
     *
     * @param nameToSearch: nome del creator da ricercare
     * @return: modello del Creator ricercato
     */
    public Creator get_json_creator(String nameToSearch) {

        String res = null;
        RestRequest rs = new RestRequest("creators", nameToSearch);
        try {
            rs.sendGet();
            res = rs.getResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Creator creator=null;
        try{

            // Creazione di un JSONObject relativo al risultato della ricerca precedente
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

            // Alcuni attributi di un Character sono opzionali, quindi si fanno controlli su valori nulli
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

            // Creazione di un oggetto Creator a partire dai dati nell'oggetto JSON in questione
            creator = new Creator( id, firstName, middleName, lastName, suffix, fullName, modified,
                    resourceURI, urlArrayList, thumbnail, comic, story, event);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return creator;
    }

    /**
     * Questo metodo restituisce un'istanza dell' oggetto Comic relativo al Character o al Creator
     * oggetto della ricerca
     *
     * @param comics: attributo comics derivante dal file JSON
     * @return: modello Comic
     */
    private Comic  get_json_comics(JSONObject comics) {

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

    /**
     * Questo metodo restituisce un'istanza dell' oggetto Event relativo al Character o al Creator
     * oggetto della ricerca
     *
     * @param events: attributo event restituito dal file JSON
     * @return: modello Event
     */
    private Event  get_json_events(JSONObject events) {

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

    /**
     * Questo metodo restituisce un'istanza dell' oggetto Stories relativo al Character o al Creator
     * oggetto della ricerca
     *
     * @param stories: attributo stories derivante dal file JSON
     * @return: modello Story
     */
    private Story  get_json_stories(JSONObject stories) {

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

    /**
     * Questo metodo restituisce un'istanza dell' oggetto Thumbnail relativo al Character o al Creator
     * oggetto della ricerca
     *
     * @param thumbnails: attributo thumbnail derivante dal file JSON
     * @return: modello Thumbnail
     */
    private Thumbnail  get_json_thumbnail(JSONObject thumbnails) {
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

    /**
     * Questo metodo restituisce un'istanza dell' oggetto urls relativo al Character o al Creator
     * oggetto della ricerca
     *
     * @param urls: attributo urls derivante dal file JSON
     * @return: Array di Url
     */
    private ArrayList<Url>  get_json_urls(JSONArray urls) {
        ArrayList<Url> urlArrayList= new ArrayList<>();

        try{
            for (int j = 0; j<urls.length();j++){
                Url singleUrl;
                String type = urls.getJSONObject(j).getString("type");
                String url = urls.getJSONObject(j).getString("url");
                singleUrl = new Url(type,url);
                if(singleUrl != null){
                    urlArrayList.add(singleUrl);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return urlArrayList;
    }

}