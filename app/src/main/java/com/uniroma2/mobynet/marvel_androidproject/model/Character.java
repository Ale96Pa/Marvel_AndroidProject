package com.uniroma2.mobynet.marvel_androidproject.model;

import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;

public class Character {

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

    public  Character(int id, String name, String description,String modified,String resourceURI){
        this.id=id;
        this.name=name;
        this.description=description;
        this.modified=modified;
        this.resourceURI=resourceURI;
    }


    public Character( int id, String name, String description, String modified, String resourceURI,ArrayList<Url> urls,
                      Thumbnail thumbnail, Comic comics, Story stories, Event events){
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

    public void setId(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getModified() {
        return modified;
    }

    public String getResourceURI() {
        return resourceURI;
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

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }



    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }

    public void setModified(String modified){
        this.modified=modified;
    }

    public void setResourceURI(String resourceURI){
        this.resourceURI=resourceURI;
    }

    public void setUrls(ArrayList<Url> urls){
        this.urls=urls;
    }

    public void setComics(Comic comics){
        this.comics=comics;
    }

    public void setStories(Story stories){
        this.stories=stories;
    }

    public void setEvents(Event events){
        this.events=events;
    }

}