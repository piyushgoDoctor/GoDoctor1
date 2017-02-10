package com.example.godoctor.myapplication1.newApp.models;

/**
 * Created by godoctor on 10/2/17.
 */

public class Page {
    String person,share,sumary,id;
    byte[] image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Page( String id,String person, String share, String sumary, byte[] image) {
        this.person = person;
        this.share = share;
        this.sumary = sumary;
        this.id = id;
        this.image = image;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
