package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tutorialsproject.Interface.CarComponent;
import com.example.tutorialsproject.Interface.DaggerCarComponent;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.Car;

import javax.inject.Inject;

public class DIActivity extends AppCompatActivity {
    //For Dependency Injection
    @Inject Car car;  //(This car variable to be injected with Car object)
                              // so instead of car = component.getCar();
                              //Dagger does not support injection into private fields
                              //so private will be removed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di);

        CarComponent component = DaggerCarComponent.create();
      //car = component.getCar();
        component.inject(this);  //this is called field injection

        car.drive();
    }

}
