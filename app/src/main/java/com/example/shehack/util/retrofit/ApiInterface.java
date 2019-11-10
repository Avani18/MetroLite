package com.example.shehack.util.retrofit;

import com.example.shehack.model.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("metro.json")
    Call<List<Station>> getStation();

    @GET("metro.json")
    Call<String> getStation2();
}
