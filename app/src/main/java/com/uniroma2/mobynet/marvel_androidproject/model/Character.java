package com.uniroma2.mobynet.marvel_androidproject.model;

import java.util.ArrayList;
import java.util.Date;

public class Character {

    private int id;
    private String name;
    private String description;
    private Date modified;
    private String resourceURI;
    private ArrayList<Url> urls;
    private Img thumbnail;
    private ArrayList<Comic> comics;
    private ArrayList<Story> stories;
    private ArrayList<Event> events;

    public Character( int id, String name, String description, Date modified, String resourceURI,ArrayList<Url> urls,
                      Img thumbnail, ArrayList<Comic> comics, ArrayList<Story> stories, ArrayList<Event> events){
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

    public void setName(String name){
        this.name=name;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setModified(Date modified){
        this.modified=modified;
    }

    public void setResourceURI(String resourceURI){
        this.resourceURI=resourceURI;
    }

    public void setUrls(ArrayList<Url> urls){
        this.urls=urls;
    }

    public void setThumbnail(Img thumbnail){
        this.thumbnail=thumbnail;
    }

    public void setComics(ArrayList<Comic> comics){
        this.comics=comics;
    }

    public void setStories(ArrayList<Story> stories){
        this.stories=stories;
    }

    public void setEvents(ArrayList<Event> events){
        this.events=events;
    }

}
