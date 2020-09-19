package com.example.tutorialsproject.Interface;

import com.example.tutorialsproject.database.model.ComplimentModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    String BASE_URL = "https://complimentr.com/";

    @GET("api")
    Call<ComplimentModel> getCompliment();
}
