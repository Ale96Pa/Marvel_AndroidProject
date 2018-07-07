package com.uniroma2.mobynet.marvel_androidproject.model;

import java.util.ArrayList;
import java.util.Date;


public class Creator {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String suffix;
    private String modified;
    private String resourceURI;
    private ArrayList<Url> urls;
    private Thumbnail thumbnail;
    private Comic comics;
    private Story stories;
    private Event events;


    public Creator( int id, String firstName, String middleName, String lastName, String suffix, String fullName, String modified, String resourceURI,ArrayList<Url> urls,
                      Thumbnail thumbnail, Comic comics, Story stories, Event events){

        this.id=id;
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.suffix=suffix;
        this.fullName=fullName;
        this.modified=modified;
        this.resourceURI=resourceURI;
        this.urls=urls;
        this.thumbnail=thumbnail;
        this.comics=comics;
        this.stories=stories;
        this.events=events;



    }

    public void setId(int id){
        this.id=id;
    }

    public void setFirstName(String name){
        this.firstName=firstName;
    }
    public String getFirstName( ){
        return firstName;
    }

    public void setMiddleName(String name){
        this.middleName=middleName;
    }

    public void setLastName(String name){
        this.lastName=lastName;
    }
    public String getLastName( ){
        return lastName;
    }

    public void setSuffix(String suffix){
        this.suffix=suffix;
    }

    public void setFullName(String name){
        this.fullName=fullName;
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

    public void setThumbnail(Thumbnail thumbnail){
        this.thumbnail=thumbnail;
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
