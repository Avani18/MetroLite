package com.example.shehack.model;

import com.google.gson.annotations.SerializedName;


public class Station {

    @SerializedName("name")
    private String name;

    @SerializedName("details")
    private Details details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station(String name, Details details)
    {
        this.name= name;
        this.details= details;
    }

}
