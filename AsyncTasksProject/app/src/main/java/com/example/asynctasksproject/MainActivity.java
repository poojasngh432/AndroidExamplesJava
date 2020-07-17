package com.example.asynctasksproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ASYNC";
    Button btnChangeColor;
    ConstraintLayout clBackground;
    ListView lvItems;

    String[] items = new String[]{
            "Alpha",
            "Beta",
            "Gamma",
            "Delta",
            "Phi",
            "Curo",
            "Strata",
            "Hump"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChangeColor = findViewById(R.id.btnChangeColor);
        clBackground = findViewById(R.id.cl);
        lvItems = findViewById(R.id.lvItems);

        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                items
        );

        lvItems.setAdapter(itemAdapter);

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler h = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "We have waited 5 seconds", Toast.LENGTH_SHORT).show();
                        clBackground.setBackgroundColor(Color.RED);
                    }
                };
        h.postDelayed(r,5000);
//                waitNSec(10);
            }
        });
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
