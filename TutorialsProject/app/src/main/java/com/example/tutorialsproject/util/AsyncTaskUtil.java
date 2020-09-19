package com.example.tutorialsproject.util;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tutorialsproject.Interface.PhoneInterface;

public class AsyncTaskUtil extends AsyncTask<String, Integer,Void> {
    private Context context;
    private PhoneInterface phoneInterface;

    public AsyncTaskUtil(Context context, PhoneInterface phoneInterface){
        this.context = context;
        this.phoneInterface = phoneInterface;
    }

    @Override
    protected Void doInBackground(String[] args) {
        UiUtil.showToast(context,"Task executing..");
        return null;
    }
}
