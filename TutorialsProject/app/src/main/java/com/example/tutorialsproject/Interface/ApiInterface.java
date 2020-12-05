package com.example.tutorialsproject.Interface;

import com.example.tutorialsproject.database.model.gifModel.GifModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    String BASE_URL = "https://complimentr.com/";
    String BASE_GIF_URL = "https://api.tenor.com/v1/";

    @GET("random?q=coding&key=ZYVWAHIDP9H6&limit=1")
    Call<GifModel> getGifResult();

}
