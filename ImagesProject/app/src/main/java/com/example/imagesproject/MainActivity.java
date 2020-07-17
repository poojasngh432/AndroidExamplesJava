package com.example.imagesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button cameraBtn, pickFromGalleryBtn, browseGalleryBtn, imageEditingBtn, picassoBtn, volleyBtn, glideBtn, frescoBtn, uniImgLoaderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraBtn = (Button) findViewById(R.id.camera_btn);
        pickFromGalleryBtn = (Button) findViewById(R.id.pick_from_gallery_btn);
        browseGalleryBtn = (Button) findViewById(R.id.browse_gallery_btn);
        imageEditingBtn = (Button) findViewById(R.id.img_edit_btn);
        picassoBtn = (Button) findViewById(R.id.picasso_btn);
        volleyBtn = (Button) findViewById(R.id.volley_btn);
        glideBtn = (Button) findViewById(R.id.glide_btn);
        frescoBtn = (Button) findViewById(R.id.fresco_btn);
        uniImgLoaderBtn = (Button) findViewById(R.id.uil_btn);
//        freezeLayoutOnTopBtn = (Button) findViewById(R.id.freeze_layout_on_top_btn);
//        scrollingBtn = (Button) findViewById(R.id.scrolling_btn);
//        permissionsBtn = findViewById(R.id.permissions_btn);
//        sendEmailBtn = findViewById(R.id.send_email_btn);
//        recyclerViewBtn = findViewById(R.id.recycler_view_btn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        pickFromGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PickFromGalleryActivity.class);
                startActivity(intent);
            }
        });
        browseGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BrowseGalleryActivity.class);
                startActivity(intent);
            }
        });
        imageEditingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageEditingActivity.class);
                startActivity(intent);
            }
        });
        picassoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PicassoActivity.class);
                startActivity(intent);
            }
        });
        volleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(intent);
            }
        });
        glideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GlideActivity.class);
                startActivity(intent);
            }
        });
        frescoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FrescoActivity.class);
                startActivity(intent);
            }
        });
        uniImgLoaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UILActivity.class);
                startActivity(intent);
            }
        });
      }
    }
}
