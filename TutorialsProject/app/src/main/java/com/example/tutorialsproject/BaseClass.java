package com.example.tutorialsproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.tutorialsproject.Interface.ApiInterface;
import com.example.tutorialsproject.database.DatabaseHelper;
import com.example.tutorialsproject.database.MainDatabase;
import com.example.tutorialsproject.database.model.gifModel.GifModel;
import com.facebook.stetho.Stetho;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClass extends Application {
    private static BaseClass instance;
    private Retrofit retrofit;
    private ApiInterface retrofitClient;
    private DatabaseHelper dbHelper;
    private String url;
    private MainDatabase mainDatabase;
    //private static ApiInterface mService;

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
            retrofitClient = retrofit.create(ApiInterface.class);

            Call<GifModel> call = retrofitClient.getGifResult();

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

    public ApiInterface getRetrofitClient(){
        if(retrofitClient == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofitClient = retrofit.create(ApiInterface.class);
        }
        return retrofitClient;
    }

    public ApiInterface getOkhttpClient(){
        //Creating a retrofit object
        if(retrofitClient == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofitClient = retrofit.create(ApiInterface.class);
        }
        return retrofitClient;
    }

    public String getUrl(){
        return url;
    }

//    public static ApiInterface getService() {
//        if (mService == null) {
//            mService = RetrofitClientInstance.createService(ApiInterface.class, BuildConfig.REST_HOST);
//        }
//        return mService;
//    }

    private void initiateDB() {
        dbHelper = new DatabaseHelper(this);
        mainDatabase = MainDatabase.getInstance(getApplicationContext());
    }

    public DatabaseHelper getDbHelper() {
        return dbHelper;
    }

    public MainDatabase getMainDatabase(){
        return mainDatabase;
    }

    public void setDbHelper(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

}
