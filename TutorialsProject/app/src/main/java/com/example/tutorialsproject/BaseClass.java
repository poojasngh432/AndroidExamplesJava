package com.example.tutorialsproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.tutorialsproject.Interface.ApiInterface;
import com.example.tutorialsproject.database.DatabaseHelper;
import com.example.tutorialsproject.database.model.gifModel.GifModel;
import com.example.tutorialsproject.util.UiUtil;
import com.facebook.stetho.Stetho;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClass extends Application {
    private static BaseClass instance;
    private Retrofit retrofit;
    private ApiInterface api;
    private DatabaseHelper dbHelper;
    private String url;

    public static BaseClass getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //UiUtil.showToast(this,"App started");
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiInterface.class);

        initiateDB();

        callApi();

    }

    private void callApi() {
        ApiTask countTask = new ApiTask();
        countTask.execute();
    }

    class ApiTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... args) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_GIF_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(ApiInterface.class);

            Call<GifModel> call = api.getGifResult();

            call.enqueue(new Callback<GifModel>() {
                @Override
                public void onResponse(Call<GifModel> call, Response<GifModel> response) {
                    if (response.isSuccessful() && response.body() != null) {
                       url = response.body().getResults().get(0).getMedia().get(0).getGif().getUrl();
                    }
                }

                @Override
                public void onFailure(Call<GifModel> call, Throwable t) {
                }
            });

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public ApiInterface getApiClient(){
        return api;
    }

    public String getUrl(){
        return url;
    }

    private void initiateDB() {
        dbHelper = new DatabaseHelper(this);
    }

    public DatabaseHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

}
