package com.uniroma2.mobynet.marvel_androidproject.model;

/**
 * Created by Francesca on 27/06/18.
 */

public class StorySummary{



    private String resourceURI;
    private String name;
    private String type;

    public StorySummary(String resourceURI, String name, String type){

        this.type = type;
        this.name = name;
        this.resourceURI = resourceURI;

    }


    public void setResourceURI(String resourceURI){
        this.resourceURI = resourceURI;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }



}