package com.uniroma2.mobynet.marvel_androidproject.model;

import java.util.ArrayList;

public class Comic {
        private int available;
        private int returned;
        private String collectionURI;
        private ArrayList<ComicSummary> items;

        public Comic(){

        }

        public Comic(int available, int returned,String collectionURI, ArrayList<ComicSummary> items){
            this.available=available;
            this.collectionURI=collectionURI;
            this.items=items;
            this.returned=returned;
        }

    public int getAvailable() {
        return available;
    }

    public int getReturned() {
        return returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public ArrayList<ComicSummary> getItems() {
        return items;
    }

    public void setAvailable(int available){
            this.available=available;
        }

        public void setReturned(int returned){
            this.returned=returned;
        }

        public void setCollectionURI(String collectionURI){
            this.collectionURI=collectionURI;
        }

        public void setItems(ArrayList<ComicSummary> items){
            this.items=items;
        }
}