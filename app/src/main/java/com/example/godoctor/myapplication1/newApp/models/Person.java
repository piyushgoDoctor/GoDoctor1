package com.example.godoctor.myapplication1.newApp.models;

/**
 * Created by godoctor on 23/1/17.
 */

public class Person {
    String name;
    String description;
    String relations;
    String category;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person(String name, String description, String relations, String category, String id) {
        this.name = name;
        this.description = description;
        this.relations = relations;
        this.category = category;
        this.id=id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRelations(String relations) {
        this.relations = relations;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }

    public String getRelations() {
        return relations;
    }

    public String getCategory() {
        return category;
    }
}
