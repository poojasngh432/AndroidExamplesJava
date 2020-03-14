package com.example.moviereviewsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.moviereviewsapp.R;
import com.example.moviereviewsapp.adapter.MoviesAdapter;
import com.example.moviereviewsapp.model.Movie;
import com.example.moviereviewsapp.model.MovieResponse;
import com.example.moviereviewsapp.rest.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    private MoviesAdapter moviesAdapter;
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesAdapter = null;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moviesAdapter);
        connectAndGetApiData();
    }

    // This method create an instance of Retrofit
// set the base url
    public void connectAndGetApiData(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiInterface movieApiInterface = retrofit.create(MovieApiInterface.class);
        Call<MovieResponse> call = movieApiInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                moviesAdapter = new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext());
                recyclerView.setAdapter(moviesAdapter);
                moviesAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Number of movies received: " + movies.size());
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
