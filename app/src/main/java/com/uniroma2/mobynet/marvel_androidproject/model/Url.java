package com.uniroma2.mobynet.marvel_androidproject.model;

public class Url{

    /* Attributi */
    private String type;
    private String url;

    /* Costruttore */
    public Url(String type, String url){
        this.type = type;
        this.url = url;
    }

    /* Metodi */
    public void setType(String type){
        this.type = type;
    }
    public void setUrl(String url){
        this.url = url;
    }
}