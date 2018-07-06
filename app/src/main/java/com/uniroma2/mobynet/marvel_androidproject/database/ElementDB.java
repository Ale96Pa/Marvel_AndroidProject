package com.uniroma2.mobynet.marvel_androidproject.database;


import android.content.ContentValues;
import android.database.Cursor;

public class ElementDB {

    /* Attributi */
    private long id;
    private String name;

    /* Costruttore */
    public ElementDB(long id, String name){
        this.id = id;
        this.name = name;
    }

    /* Metodi */
    public ElementDB(Cursor cursor) {
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        return contentValues;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
