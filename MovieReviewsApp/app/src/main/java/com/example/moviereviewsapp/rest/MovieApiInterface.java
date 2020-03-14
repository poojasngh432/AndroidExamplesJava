package com.example.moviereviewsapp.rest;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviereviewsapp.activity.MainActivity;
import com.example.moviereviewsapp.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiInterface {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
