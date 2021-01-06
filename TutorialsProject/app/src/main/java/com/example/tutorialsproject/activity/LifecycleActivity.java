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
    private static final String TAG = "TESTING";
    private String name = "CAPTAIN AMERICA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        Log.d(TAG,"onCreate " + name);

        // restore saved state
        if (savedInstanceState != null) {
            name = savedInstanceState.getString("name");
            Log.d(TAG,"onCreate inside " + name);
        }

        findViewById(R.id.btn_activity_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifecycleActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // save current instance state
        name = "TONY STARK";
        outState.putString("name",name);
        super.onSaveInstanceState(outState);

        Log.d(TAG,"onSaveInstanceState " + name);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // restore saved state
        super.onRestoreInstanceState(savedInstanceState);
        name = savedInstanceState.getString("name");

        if (savedInstanceState != null) {
            Log.d(TAG,"onRestoreInstanceState " + name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart " + name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume " + name);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy method");
    }

}
