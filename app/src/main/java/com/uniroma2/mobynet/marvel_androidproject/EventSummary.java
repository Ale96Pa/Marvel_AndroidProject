package com.uniroma2.mobynet.marvel_androidproject;

/**
 * Created by Francesca on 27/06/18.
 */

public class EventSummary{



    private String resourceURI;
    private String name;

    public EventSummary(String resourceURI, String name){

        this.name = name;
        this.resourceURI = resourceURI;

    }


    public void setResourceURI(String resourceURI){
        this.resourceURI = resourceURI;
    }

    public void setName(String name){
        this.name = name;
    }




}