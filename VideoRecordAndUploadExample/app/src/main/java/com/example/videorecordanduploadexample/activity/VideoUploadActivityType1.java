package com.example.videorecordanduploadexample.activity;

import android.os.Bundle;

import com.example.videorecordanduploadexample.R;

public class VideoUploadActivityType1 extends BaseCameraActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_upload_type1);
        onCreateActivity();
        videoWidth = 720;
        videoHeight = 1280;
        cameraWidth = 1280;
        cameraHeight = 720;
    }
}
