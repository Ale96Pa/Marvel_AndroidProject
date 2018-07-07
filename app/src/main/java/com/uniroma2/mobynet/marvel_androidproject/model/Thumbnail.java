package com.uniroma2.mobynet.marvel_androidproject.model;

import android.net.Uri;

public class Thumbnail {
    private String path;
    private String extension;

    public Thumbnail(){}
    public Thumbnail(String path, String extension){
        this.path=path;
        this.extension=extension;
    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    public void setPath(String path){
        this.path=path;
    }

    public void setExtension(String extension){
        this.extension = extension;
    }
}