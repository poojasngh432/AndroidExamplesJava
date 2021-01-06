package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tutorialsproject.R;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DataStructuresActivity extends AppCompatActivity {
    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_structures);

        names = new LinkedList<>();
    }
}
