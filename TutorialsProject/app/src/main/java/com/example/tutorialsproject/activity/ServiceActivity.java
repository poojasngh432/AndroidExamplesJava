package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tutorialsproject.BackgroundService;
import com.example.tutorialsproject.ForegroundService;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.helper.MyService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonStart, buttonStop, buttonNext, fgStartServiceBtn, fgStopServiceBtn, bgServiceBtn;
    String serviceText = "Start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        buttonNext =  findViewById(R.id.buttonNext);
        fgStartServiceBtn = findViewById(R.id.start_foreground_service);
        fgStopServiceBtn = findViewById(R.id.stop_foreground_service);
        bgServiceBtn = findViewById(R.id.bg_service);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        fgStartServiceBtn.setOnClickListener(this);
        fgStopServiceBtn.setOnClickListener(this);
        bgServiceBtn.setOnClickListener(this);

        getApplication();
    }

    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.buttonStart:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.buttonStop:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.buttonNext:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.start_foreground_service:
                Intent serviceIntent = new Intent(this, ForegroundService.class);
                serviceIntent.putExtra("inputExtra", "data");
                ContextCompat.startForegroundService(this, serviceIntent);
                break;
            case R.id.stop_foreground_service:
                Intent intent1 = new Intent(this, ForegroundService.class);
                stopService(intent1);
                break;
            case R.id.bg_service:
                if (serviceText.equals("Start")) {
                    startService(new Intent(this, BackgroundService.class));
                    serviceText = "Stop";
                } else {
                    stopService(new Intent(this, BackgroundService.class));
                    serviceText = "Start";
                }
                break;
        }
    }

}
