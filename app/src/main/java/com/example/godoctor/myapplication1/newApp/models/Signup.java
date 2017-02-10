package com.example.godoctor.myapplication1.newApp.models;

/**
 * Created by godoctor on 9/2/17.
 */

public class Signup {

    String id;
    String name;
    String des;
    String imageUrl;

    public Signup(String id, String name, String des, String imageUrl) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.imageUrl = imageUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
