package com.example.tutorialsproject.Interface;

import com.example.tutorialsproject.activity.DIActivity;

import dagger.Component;

@Component
public interface CarComponent {
    //The Injector

   // Car getCar();

    void inject(DIActivity diActivity);
}
