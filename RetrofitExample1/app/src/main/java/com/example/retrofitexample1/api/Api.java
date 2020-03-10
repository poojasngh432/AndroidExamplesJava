package com.example.retrofitexample1.api;

import com.example.retrofitexample1.models.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";   //Root url

    @GET("marvel")
    Call<List<Hero>> getHeroes();   //further address of the url
}
