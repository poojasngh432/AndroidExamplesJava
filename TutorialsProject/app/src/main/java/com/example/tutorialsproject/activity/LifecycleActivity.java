package com.example.tutorialsproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.tutorialsproject.R;

public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        findViewById(R.id.btn_activity_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifecycleActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        Log.d("TESTING","onCreate method");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TESTING","onStart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TESTING","onResume method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TESTING","onPause method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TESTING","onStop method");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("TESTING","onSaveInstanceState method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TESTING","onRestart method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TESTING","onDestroy method");
    }
}
