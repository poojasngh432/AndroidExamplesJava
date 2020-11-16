package com.assignment.pristyncareproject.data.model;

import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("photos")
    private Photos_ photos;
    @SerializedName("stat")
    private String stat;

    public Photos_ getPhotos() {
        return photos;
    }

    public void setPhotos(Photos_ photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
