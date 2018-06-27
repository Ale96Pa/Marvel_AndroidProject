package com.uniroma2.mobynet.marvel_androidproject.model;

import java.util.ArrayList;

public class Event{

    private int available;
    private int returned;
    private String collectionURI;
    private ArrayList<EventSummary> items;

    public Event(int available, int returned, String collectionURI, ArrayList<EventSummary> items ){

        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
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

    public void setItems(ArrayList<EventSummary> items){
        this.items = items;
    }

}