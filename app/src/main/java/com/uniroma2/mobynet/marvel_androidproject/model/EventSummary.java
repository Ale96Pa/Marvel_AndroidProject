package com.uniroma2.mobynet.marvel_androidproject.model;

public class EventSummary{

    /* Attributi */
    private String resourceURI;
    private String name;

    /* Costruttore */
    public EventSummary(String resourceURI, String name){
        this.name = name;
        this.resourceURI = resourceURI;
    }

    /* Metodi */
    public String getResourceURI() {
        return resourceURI;
    }
    public String getName() {
        return name;
    }
    public void setResourceURI(String resourceURI){
        this.resourceURI = resourceURI;
    }
    public void setName(String name){
        this.name = name;
    }

}