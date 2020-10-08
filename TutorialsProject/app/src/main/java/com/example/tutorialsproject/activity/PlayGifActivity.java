package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tutorialsproject.BaseClass;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.util.UiUtil;

public class PlayGifActivity extends AppCompatActivity {
    String gifUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_gif);

        gifUrl = BaseClass.getInstance().getUrl();

        ImageView imageView = findViewById(R.id.image_loading);

        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.film)
                .error(android.R.drawable.stat_notify_error);

        Glide.with(getApplicationContext())
                .setDefaultRequestOptions(option)
                .load(gifUrl)
                .into(imageView);

        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               // finish();
                UiUtil.showToast(getApplicationContext(),"5 seconds have passed.");
            }
        }, 5000);
    }
}
