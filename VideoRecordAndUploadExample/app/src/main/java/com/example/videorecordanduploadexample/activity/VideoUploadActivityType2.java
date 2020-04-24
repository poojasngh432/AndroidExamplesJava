package com.example.videorecordanduploadexample.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videorecordanduploadexample.R;
import com.example.videorecordanduploadexample.compression.SiliCompressor;
import com.example.videorecordanduploadexample.recorder.CameraView;
import com.example.videorecordanduploadexample.recorder.listener.CameraListener;
import com.example.videorecordanduploadexample.recorder.listener.ErrorListener;
import com.example.videorecordanduploadexample.recorder.listener.RecordStateListener;
import com.example.videorecordanduploadexample.recorder.listener.SimpleClickListener;
import com.example.videorecordanduploadexample.recorder.util.ExtractVideoInfoUtil;
import com.example.videorecordanduploadexample.recorder.util.FileUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;
import java.util.List;


public class VideoUploadActivityType2 extends AppCompatActivity {
    private TextView tv_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_upload_type_2);

        tv_record = findViewById(R.id.tv_record);

        tv_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                Intent intent = new Intent(VideoUploadActivityType2.this, VideoCameraActivity.class);
                                startActivityForResult(intent, 100);
            }
        });
    }

}
