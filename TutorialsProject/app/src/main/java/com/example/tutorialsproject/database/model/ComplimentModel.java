package com.example.tutorialsproject.database.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class ComplimentModel extends BaseObservable {

    @Bindable
    String compliment = "";

    public ComplimentModel(String compliment){
        this.compliment = compliment;
    }

    public ComplimentModel(){

    }

    @Bindable
    public String getCompliment() {
        return compliment;
    }

    public void setCompliment(String compliment) {
        this.compliment = compliment;
        notifyPropertyChanged(BR.compliment);
    }
}
