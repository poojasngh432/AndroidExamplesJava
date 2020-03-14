package com.example.glideexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList imageUrlList = prepareData();
        ArrayList nameOfImagesList = namesData();
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), imageUrlList, nameOfImagesList);
        recyclerView.setAdapter(dataAdapter);
    }

    private ArrayList prepareData() {

// here you should give your image URLs and that can be a link from the Internet
                String imageUrls[] = {
                "https://2-photos.motorcar.com/used-1967-chevrolet-camaro_rs-sold-6046-16413658-1-640.jpg",
                "https://coolmaterial.com/wp-content/uploads/2012/05/1966-Shelby-Cobra-427.jpg",
                "https://coolmaterial.com/wp-content/uploads/2012/05/1962-Ferrari-250.jpg",
                "https://s8096.pcdn.co/wp-content/uploads/2015/02/1956-1959-BMW-507.jpg",
                "https://s8096.pcdn.co/wp-content/uploads/2015/02/1960-1962-Ghia.jpg",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c2aecad5_-_100-cars-011-volvo-p1800-1211-xln.jpg?resize=980:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c2ca3397_-_100-cars-016-alfa-romeo-spider-1211-xln.jpg?resize=980:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/17/03/1600x1097/alfa_romeo-4c-2014-1600-06.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c346750f_-_100-cars-037-triumph-tr6-1211-xln-30755611.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/17/03/1600x1020/lamborghini-huracan.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/17/03/1600x1048/tesla-model_s-2013-1600-06.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c39b127d_-_100-cars-052-mg-mga-1211-xln-65250390.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c4aa0854_-_100-cars-096-jaguar-e-type-1211-xln.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c46c0670_-_100-cars-086-aston-martin-db9-1211-xln-64235997.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c42931c7_-_100-cars-075-porsche-carrera-27rs-1211-xln.jpg?resize=768:*",
                "https://hips.hearstapps.com/pop.h-cdn.co/assets/cm/15/05/768x516/54ca5c3d9c142_-_100-cars-062-ferrari-550-maranello-1211-xln-46381994.jpg?resize=768:*"};

        ArrayList imageUrlList = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(imageUrls[i]);
            imageUrlList.add(imageUrl);
        }
        Log.d("MainActivity", "List count: " + imageUrlList.size());
        return imageUrlList;
    }

    private ArrayList namesData(){
                String names[] = {
                "Chevrolet Camaro 1967",
                "1966 Shelby 427 Cobra",
                "1969 Toyota 2000GT",
                "1956-1959 BMW 507",
                "1960-1962 GHIA",
                "Volvo P1800 (1961–1973)",
                "Alfa Romeo Spider (1966–1969)",
                "Alfa Romeo 4C (2015-present)",
                "Triumph TR6 (1969–1976)",
                "Lamborghini Huracan (2014-present)",
                "Tesla Model S",
                "MG MGA (1955–1962)",
                "Jaguar E-Type (1961–1975)",
                "Aston Martin DB9 (2004–2011)",
                "Porsche Carrera 2.7 RS (1973)",
                "Ferrari 550 Maranello (1996–2001)"};
        ArrayList nameOfImagesList = new ArrayList<>();
        for(String n: names){
            nameOfImagesList.add(n);
        }
        return nameOfImagesList;
    }
}
