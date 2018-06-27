package com.uniroma2.mobynet.marvel_androidproject;

import java.util.ArrayList;

/**
 * Created by Francesca on 27/06/18.
 */

public class Story{


    private int available;
    private int returned;
    private String collectionURI;
    private ArrayList<StorySummary> items;

    public Story(int available, int returned, String collectionURI, ArrayList<StorySummary> items ){

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

    public void setItems(ArrayList<StorySummary> items){
        this.items = items;
    }



}