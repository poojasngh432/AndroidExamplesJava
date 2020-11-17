package com.assignment.pristyncareproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.assignment.pristyncareproject.data.model.Photo;
import com.assignment.pristyncareproject.view.activity.MainActivity;

import java.util.List;

public class PhotoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Photo>> allPhotos;

    public PhotoViewModel(@NonNull Application application) {
        super(application);
    }

    public void addPhotos(List<Photo> photos){
        MainActivity.photosData.addAll(photos);
        MainActivity.photosLiveData.setValue(MainActivity.photosData);
    }

    public MutableLiveData<List<Photo>> getAllPhotos(){
        return MainActivity.photosLiveData;
    }
}
