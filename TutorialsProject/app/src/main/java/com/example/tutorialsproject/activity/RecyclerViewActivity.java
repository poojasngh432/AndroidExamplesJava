package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.adapter.CustomAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    String[] list = {"The Social Network", "The Matrix", "The Pirates of Silicon Valley", "The Imitation Game", "The Internship", "Jobs", "Mr. Robot", "Silion Valley", "Person of Interest", "Halt and Catch Fire", "Scorpion", "The Social Network", "The Matrix", "The Pirates of Silicon Valley", "The Imitation Game", "The Internship", "Jobs", "Mr. Robot", "Silion Valley", "Person of Interest", "Halt and Catch Fire", "Scorpion"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Log.d("RECYCLERVIEWTEST","starting RecyclerView..");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        CustomAdapter adapter = new CustomAdapter(list);
        recyclerView.setHasFixedSize(true); //when changing the contents of the adapter doesn't affect the size of rv
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
