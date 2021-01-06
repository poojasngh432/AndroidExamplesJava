package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.fragment.FirstFragment;
import com.example.tutorialsproject.fragment.Fragment1;

public class FragmentActivity extends AppCompatActivity {
    private static final String TAG = "FRAGMENTACTIVITYEXAMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // save current instance state
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState FragmentActivity");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // restore saved state
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart FragmentActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume FragmentActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause method FragmentActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop method FragmentActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart method FragmentActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy method FragmentActivity");
    }

    public void onClickFragment1(View view) {
        Intent intent = new Intent(this,FragmentEx1Activity.class);
        startActivity(intent);
    }

    public void onClickFragment2(View view) {
        Intent intent = new Intent(this,FragmentEx2Activity.class);
        startActivity(intent);
    }

    public void onClickFragment3(View view) {
        Intent intent = new Intent(this,FragmentEx1Activity.class);
        startActivity(intent);
    }

    public void onClickFragment4(View view) {
        Intent intent = new Intent(this,FragmentEx1Activity.class);
        startActivity(intent);
    }

    public void onClickFragment5(View view) {
        Intent intent = new Intent(this,FragmentEx1Activity.class);
        startActivity(intent);
    }

}

