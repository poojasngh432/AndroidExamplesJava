package com.example.bloggerapiexample;

import com.example.bloggerapiexample.models.Item;
import com.example.bloggerapiexample.models.PostList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerApi {

    //[From Blogger API site: https://developers.google.com/blogger/docs/3.0/using]
    //Access post by Id = BASE_URL + /postId

    private static final String KEY = "";
    private static final String URL = "https://www.googleapis.com/blogger/v3/blogs/1179786062965064468/posts/";

    public static PostService postService = null; //we're using Singleton pattern so that only retrofit object is created only one time
    public static PostService getPostService(){
        if(postService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService{
        @GET("?key="+KEY)
        Call<PostList> getPostList();
    }
}
