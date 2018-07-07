package com.uniroma2.mobynet.marvel_androidproject.model;

public class EventSummary{

    private String resourceURI;
    private String name;

    public EventSummary(String resourceURI, String name){
        this.name = name;
        this.resourceURI = resourceURI;
    }

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