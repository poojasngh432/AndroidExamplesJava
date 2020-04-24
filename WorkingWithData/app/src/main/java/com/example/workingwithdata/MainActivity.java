package com.example.workingwithdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sharedPrefsBtn, sqliteDBBtn, internalStorageBtn, externalStorageBtn, cacheBtn, onSaveInsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefsBtn = (Button) findViewById(R.id.shared_prefs_btn);
        sqliteDBBtn = findViewById(R.id.sqlite_db_btn);
        internalStorageBtn = findViewById(R.id.internal_storage_btn);
        externalStorageBtn = findViewById(R.id.external_storage_btn);
        cacheBtn = findViewById(R.id.cache_btn);
        onSaveInsBtn = findViewById(R.id.onsaveinstace_btn);

        sharedPrefsBtn.setOnClickListener(this);
        sqliteDBBtn.setOnClickListener(this);
        internalStorageBtn.setOnClickListener(this);
        externalStorageBtn.setOnClickListener(this);
        cacheBtn.setOnClickListener(this);
        onSaveInsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.shared_prefs_btn){
            Intent i1 = new Intent(this, SharedPrefsActivity.class);
            startActivity(i1);
        }else if(view.getId() == R.id.sqlite_db_btn){
            Intent i2 = new Intent(this, SqliteDBActivity.class);
            startActivity(i2);
        }else if(view.getId() == R.id.internal_storage_btn){
            Intent i3 = new Intent(this, InternalStorageActivity.class);
            startActivity(i3);
        }else if(view.getId() == R.id.external_storage_btn){
            Intent i4 = new Intent(this, ExternalStorageActivity.class);
            startActivity(i4);
        }else if(view.getId() == R.id.cache_btn){
            Intent i5 = new Intent(this, CacheActivity.class);
            startActivity(i5);
        }else if(view.getId() == R.id.onsaveinstace_btn){
            Intent i6 = new Intent(this, OnSaveInsActivity.class);
            startActivity(i6);
        }
    }
}
