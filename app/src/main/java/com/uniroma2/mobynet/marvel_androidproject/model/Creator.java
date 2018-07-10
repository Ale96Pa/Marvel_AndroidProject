package com.uniroma2.mobynet.marvel_androidproject.model;

import java.util.ArrayList;

public class Creator {

    /* Attributi */
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

    /* Costruttore */
    public Creator( int id, String firstName, String middleName, String lastName, String suffix,
                    String fullName, String modified, String resourceURI,ArrayList<Url> urls,
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


    /* Metodi */

    public int getId() {
        return id;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getFullName() {
        return fullName;
    }
    public String getSuffix() {
        return suffix;
    }
    public String getModified() {
        return modified;
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
    public String getFirstName( ){
        return firstName;
    }
    public String getLastName( ){
        return lastName;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setModified(String modified){
        this.modified=modified;
    }

}