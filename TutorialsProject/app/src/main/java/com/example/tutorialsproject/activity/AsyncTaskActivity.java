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

    public static final String TAG = "AsyncTaskActivity";
    TextView tvCounter;
    Button btnStart, btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        tvCounter = findViewById(R.id.tvCounter);
        btnStart = findViewById(R.id.btnStart);
        btnRandom = findViewById(R.id.btnRandom);

        Log.d(TAG,"onCreate");

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
                countTask.execute(10);  //uses SERIAL_EXECUTER to run task sequentially
                //to run many asynctasks in parallel, use THREAD_POOL_EXECUTER
                //for (Request req : allPendingRequest) {
                //new BatchTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, req);
                //}

                /**
                 * This AsyncTask.THREAD_POOL_EXECUTOR will queue up all the task and execute it 5 tasks at a time
                 * and when any of running task is completed, the next one from the queue is executed.
                 * This is repeated until the queue becomes empty. A
                 * syncTask.THREAD_POOL_EXECUTOR cannot queue more than 128 tasks at a time.
                 * So if we submit more than that, an exception will be thrown.
                 */
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
            Log.d(TAG,"value : " + values[0]);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}
