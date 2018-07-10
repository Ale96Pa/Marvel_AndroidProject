package com.uniroma2.mobynet.marvel_androidproject.model;

public class StorySummary{

    /* Attributi */
    private String resourceURI;
    private String name;
    private String type;

    /* Costruttore */
    public StorySummary(String resourceURI, String name, String type){
        this.type = type;
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
    public String getType() {
        return type;
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