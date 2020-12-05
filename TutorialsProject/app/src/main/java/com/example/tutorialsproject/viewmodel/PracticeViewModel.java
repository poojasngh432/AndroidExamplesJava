package com.example.tutorialsproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tutorialsproject.util.RandomUtils;

public class PracticeViewModel extends ViewModel {

    public int randomNum;
    private MutableLiveData<String> name = new MutableLiveData<>();

    public void setName(String name) {
        this.name.setValue(name);
    }

    public LiveData<String> getName() {
        return name;
    }

    public int generateRandomNum(){

        if(randomNum == 0){
            randomNum = createRandomNum(100);
        }

        return randomNum;
    }

    public int createRandomNum(int n){

        randomNum = RandomUtils.getRandom(n);

        return randomNum;
    }

}
