package com.example.shehack.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
class Details {
    @SerializedName("line")
    private List<String> line;

    @SerializedName("layout")
    String layout;

    @SerializedName("longitude")
    double longitude;

    @SerializedName("latitude")
    double latitude;


    public List<String> getLine() {
        return line;
    }

    public void setLine(List<String> line) {
        this.line = line;
    }

}
