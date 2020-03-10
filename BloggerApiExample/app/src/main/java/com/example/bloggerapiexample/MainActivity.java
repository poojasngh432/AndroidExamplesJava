package com.example.bloggerapiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bloggerapiexample.models.PostList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //STEPS:
    //1. Created a new blog on blogger.com -
    //2. added 4 posts in it. https://www.blogger.com/blogger.g?blogID=1179786062965064468#allposts
    //3. got the blogId from the above url(1179786062965064468)
    //4. created an API Key from blogger API and added her in BloggerApi.KEY = "AIzaSyBc2GPLt05C8JQKdS4R2tUwsqihp1ISQsU"
    //5. added base_url = https://www.googleapis.com/blogger/v3/blogs/blogId/posts
    //6. got json data url = https://www.googleapis.com/blogger/v3/blogs/1179786062965064468/posts?key=AIzaSyBc2GPLt05C8JQKdS4R2tUwsqihp1ISQsU
    //7. went to - http://www.jsonschema2pojo.org/  and created all POJO classes from JSON data
    //8. pasted them here in models package
    //9. created a BloggerApi class to make retrofit object only once using Singleton pattern
    //10. made a PostService interface in BloggerApi class to make the GET request calls
    //11. Fetched data here in MainActivity using Call interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }

    private void getData(){
        Call<PostList> postList = BloggerApi.getPostService().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
