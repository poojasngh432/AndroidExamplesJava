package com.assignment.pristyncareproject.data.Interface;

import com.assignment.pristyncareproject.data.model.Photo;
import com.assignment.pristyncareproject.data.model.Photos;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL = "https://api.flickr.com/";

    @GET("productsData")
    Observable<List<Photo>> getPhotos();

    @GET("services/rest/?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&safe_search=1&tags=kitten&per_page=10")
    Call<Photos> getPhotosList(
            @Query("page") String page
    );

}
