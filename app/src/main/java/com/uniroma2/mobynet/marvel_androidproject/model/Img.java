package com.uniroma2.mobynet.marvel_androidproject.model;

public class Img {
    private String path;
    private String extension;

    public Img(String path, String extension){
        this.path=path;
        this.extension=extension;
    }

    public void setPath(String path){
        this.path=path;
    }

    public void setExtension(String extension){
        this.extension = extension;
    }
}
