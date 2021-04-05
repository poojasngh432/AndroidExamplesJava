package com.example.tutorialsproject.Interface;

import com.example.tutorialsproject.database.model.FeedItemModel;
import com.example.tutorialsproject.database.model.gifModel.GifModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL = "https://complimentr.com/";
    String BASE_GIF_URL = "https://api.tenor.com/v1/";

    @GET("random?q=coding&key=ZYVWAHIDP9H6&limit=1")
    Call<GifModel> getGifResult();

    @GET("api/feed")
    Call<FeedItemModel> getFeedItems(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("version") int version, //represents version required for expert welcome item in feed
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("android_id") String androidId,
            @Query("appinstance_id") String appInstanceId,
            @Query("user_type") int userType,
            @Query("item_serve") String itemServe,
            @Query("p_count") int pCount,
            @Query("review_feed") int reviewFeed
    );

    String BASE_URL_NEW = "https://demo7846676.mockable.io/";

    @GET("api/feed")
    Observable<FeedItemModel> getProducts( @Header("Authorization") String token,
                                           @Query("page") int page,
                                           @Query("version") int version, //represents version required for expert welcome item in feed
                                           @Query("lat") String lat,
                                           @Query("lon") String lon,
                                           @Query("android_id") String androidId,
                                           @Query("appinstance_id") String appInstanceId,
                                           @Query("user_type") int userType,
                                           @Query("item_serve") String itemServe,
                                           @Query("p_count") int pCount,
                                           @Query("review_feed") int reviewFeed);

}
