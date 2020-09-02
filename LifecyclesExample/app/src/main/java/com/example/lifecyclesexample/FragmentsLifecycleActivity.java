package com.example.lifecyclesexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class FragmentsLifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_lifecycle);
        Log.d("TESTING","onCreate method B");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TESTING","onStart method B");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TESTING","onResume method B");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TESTING","onPause method B");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TESTING","onStop method B");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("TESTING","onSaveInstanceState method B");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TESTING","onRestart method B");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TESTING","onDestroy method B");
    }
}
