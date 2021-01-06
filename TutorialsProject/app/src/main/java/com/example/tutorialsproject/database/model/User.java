package com.example.tutorialsproject.database.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.tutorialsproject.BR;

public class User extends BaseObservable {
    private String firstName, lastName;

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.example.tutorialsproject.BR.firstName);

    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(com.example.tutorialsproject.BR.lastName);

    }
}
