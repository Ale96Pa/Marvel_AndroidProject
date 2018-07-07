package com.uniroma2.mobynet.marvel_androidproject.model;

public class ComicSummary {
    private String resourceURI;
    private String name;

    public ComicSummary(String resourceURI, String name){
        this.resourceURI=resourceURI;
        this.name=name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
}