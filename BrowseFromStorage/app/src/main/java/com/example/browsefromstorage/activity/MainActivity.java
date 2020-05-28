package com.example.browsefromstorage.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.browsefromstorage.R;
import com.example.browsefromstorage.activity.SelectImageActivity;
import com.example.browsefromstorage.activity.SelectMultipleImagesActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button selectImgBtn, selectMultipleImgsBtn, selectFileBtn, selectMusicBtn, selectVideoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectImgBtn = (Button) findViewById(R.id.select_img_btn);
        selectMultipleImgsBtn = findViewById(R.id.select_multiple_imgs_btn);
        selectFileBtn = findViewById(R.id.select_a_file_btn);
        selectMusicBtn = findViewById(R.id.select_music_btn);
        selectVideoBtn = findViewById(R.id.select_video_btn);

        selectImgBtn.setOnClickListener(this);
        selectMultipleImgsBtn.setOnClickListener(this);
        selectFileBtn.setOnClickListener(this);
        selectMusicBtn.setOnClickListener(this);
        selectVideoBtn.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.select_img_btn){
            Intent i1 = new Intent(this, SelectImageActivity.class);
            startActivity(i1);
        }else if(view.getId() == R.id.select_multiple_imgs_btn){
            Intent i2 = new Intent(this, SelectMultipleImagesActivity.class);
            startActivity(i2);
        }else if(view.getId() == R.id.select_a_file_btn){
            Intent i3 = new Intent(this, SelectFileActivity.class);
            startActivity(i3);
        }else if(view.getId() == R.id.select_music_btn){
            Intent i4 = new Intent(this, SelectMusicActivity.class);
            startActivity(i4);
        }else if(view.getId() == R.id.select_video_btn) {
            Intent i5 = new Intent(this, SelectVideoActivity.class);
            startActivity(i5);
        }
    }
}
