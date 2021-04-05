package com.example.tutorialsproject.database.model;

import com.example.tutorialsproject.util.ApiRequestStatus;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeneralResponseModel implements Serializable {

    @SerializedName("status")
    private boolean status;
    @SerializedName("errorMsg")
    private String errorMsg;

    @SerializedName("code")
    private int code;

    private ApiRequestStatus apiStatus;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public  String getErrorMsg(){
        return  this.errorMsg;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ApiRequestStatus getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(ApiRequestStatus apiStatus) {
        this.apiStatus = apiStatus;
    }
}

