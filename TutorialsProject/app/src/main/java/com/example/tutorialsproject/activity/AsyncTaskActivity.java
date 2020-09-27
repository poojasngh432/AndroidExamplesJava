package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutorialsproject.R;

import java.util.Random;

public class AsyncTaskActivity extends AppCompatActivity {

    public static final String TAG = "AT";
    TextView tvCounter;
    Button btnStart, btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        tvCounter = findViewById(R.id.tvCounter);
        btnStart = findViewById(R.id.btnStart);
        btnRandom = findViewById(R.id.btnRandom);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                tvCounter.setText(String.valueOf(r.nextInt(1000)));
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountTask countTask = new CountTask();
                countTask.execute(10);
            }
        });
    }

    class CountTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d(TAG,"doInBackground: started");
            int n = integers[0];
            for(int i = 0; i <= n; i++){
                wait1Sec();
                publishProgress(i);
            }
            waitNSec(n);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvCounter.setText(String.valueOf(values[0]));
        }
    }

    void wait1Sec(){
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < startTime + 1000);
    }
    void waitNSec(int n){
        for(int i = 0; i < n; i++){
            wait1Sec();
        }
    }
}
