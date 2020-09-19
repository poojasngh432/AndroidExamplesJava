package com.example.tutorialsproject.util;

public class SingletonClass {
    private static SingletonClass instance;
    public int a = 1;

    private SingletonClass(){

    }

    public static SingletonClass getInstance(){
        if(instance == null){
            instance = new SingletonClass();
        }

        return instance;
    }

    public void setState(int state) {
        instance.a = state;
    }
    public int getState() {
        return a;
    }
}
