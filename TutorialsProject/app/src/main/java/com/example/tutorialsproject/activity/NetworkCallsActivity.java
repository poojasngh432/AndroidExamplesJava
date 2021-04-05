package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tutorialsproject.BaseClass;
import com.example.tutorialsproject.Interface.ApiInterface;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.FeedItemModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkCallsActivity extends AppCompatActivity {
    private final String TAG = NetworkCallsActivity.this.getClass().getName();
    @BindView(R.id.retrofitBtn)
    Button retrofitBtn;
    @BindView(R.id.okhttpBtn)
    Button okhttpBtn;
    @BindView(R.id.rxjavaBtn)
    Button rxjavaBtn;
    @BindView(R.id.volleyBtn)
    Button volleyBtn;
    Context mContext;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_calls);

        ButterKnife.bind(this);
        mContext = NetworkCallsActivity.this;

        retrofitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call takes 2 seconds
                Date before = Calendar.getInstance().getTime();
                Log.d(TAG,before.toString());
                callApi();
            }
        });
        okhttpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1-2 seconds
                Date before = Calendar.getInstance().getTime();
                Log.d(TAG,before.toString());
                callOkHttpApi();
            }
        });
        rxjavaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date before = Calendar.getInstance().getTime();
                Log.d(TAG,before.toString());
                callApi();
            }
        });
        volleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date before = Calendar.getInstance().getTime();
                Log.d(TAG,before.toString());
                callApi();
            }
        });
    }

    private void callOkHttpApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        callEndPoints();
    }

    private void callEndPoints() {

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Observable<FeedItemModel> observable = apiInterface.getProducts("", 1, 5,
                "",
                "",
                BaseActivity.ANDROIDID,
                "259afaa5-84a7-5f47-8a27-45e9479f221e",
                1,
                "", 1, 0);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<FeedItemModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FeedItemModel productsList) {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
                Date after = Calendar.getInstance().getTime();
                Log.d(TAG,"Error " + after.toString());
            }

            @Override
            public void onComplete() {
                Toast.makeText(mContext, "Success", Toast.LENGTH_LONG).show();
                Date after = Calendar.getInstance().getTime();
                Log.d(TAG,"SUCCESS: " + after.toString());
            }
        });
    }

    private void callApi() {
        ApiInterface apiInterface = BaseClass.getInstance().getRetrofitClient();
        Call<FeedItemModel> call = apiInterface.getFeedItems("", 1, 5,
                "",
                "",
                BaseActivity.ANDROIDID,
                "259afaa5-84a7-5f47-8a27-45e9479f221e",
                1,
                "", 1, 0
        );

        call.enqueue(new Callback<FeedItemModel>() {
            @Override
            public void onResponse(Call<FeedItemModel> call, Response<FeedItemModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Success", Toast.LENGTH_LONG).show();
                    Date after = Calendar.getInstance().getTime();
                    Log.d(TAG,"SUCCESS: " + after.toString());
                }
            }

            @Override
            public void onFailure(Call<FeedItemModel> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                Date after = Calendar.getInstance().getTime();
                Log.d(TAG,"FAILURE: " + after.toString());
            }
        });
    }
}