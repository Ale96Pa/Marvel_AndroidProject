package com.uniroma2.mobynet.marvel_androidproject.model;

import java.util.ArrayList;

public class Character {

    /* Attributi */
    private int id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private ArrayList<Url> urls;
    private Thumbnail thumbnail;
    private Comic comics;
    private Story stories;
    private Event events;

    /* Costruttore */
    public Character( int id, String name, String description, String modified, String resourceURI,
                      ArrayList<Url> urls, Thumbnail thumbnail, Comic comics, Story stories, Event events){
        this.comics=comics;
        this.description=description;
        this.events=events;
        this.id=id;
        this.modified=modified;
        this.name=name;
        this.resourceURI=resourceURI;
        this.stories=stories;
        this.urls=urls;
        this.thumbnail=thumbnail;
    }

    /* Metodi */
    public int getId() {
        return id;
    }
    public ArrayList<Url> getUrls() {
        return urls;
    }
    public Thumbnail getThumbnail() {
        return thumbnail;
    }
    public Comic getComics() {
        return comics;
    }
    public Story getStories() {
        return stories;
    }
    public Event getEvents() {
        return events;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getModified(){
        return modified;
    }
    public String getResourceURI(){
        return resourceURI;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setModified(String modified){
        this.modified=modified;
    }

}