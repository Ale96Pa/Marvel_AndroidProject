package com.uniroma2.mobynet.marvel_androidproject.model;

import java.util.ArrayList;

public class Story{

    /* Attributi */
    private int available;
    private int returned;
    private String collectionURI;
    private ArrayList<StorySummary> items;

    /* Costruttori */
    public Story(){}
    public Story(int available, int returned, String collectionURI, ArrayList<StorySummary> items ){
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    /* Metodi */
    public int getAvailable() {
        return available;
    }
    public int getReturned() {
        return returned;
    }
    public String getCollectionURI() {
        return collectionURI;
    }
    public ArrayList<StorySummary> getItems() {
        return items;
    }
    public void setAvailable(int av){
        this.available = av;
    }
    public void setReturned(int ret){
        this.returned = ret;
    }
    public void setCollectionURI(String collection){
        this.collectionURI = collection;
    }
    public void setItems(ArrayList<StorySummary> items){
        this.items = items;
    }

}