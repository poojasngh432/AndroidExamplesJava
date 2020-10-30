package com.assignment.moveinsyncassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.assignment.moveinsyncassignment.Interface.ApiInterface;
import com.assignment.moveinsyncassignment.adapter.ProductAdapter;
import com.assignment.moveinsyncassignment.adapter.ProductCategoryAdapter;
import com.assignment.moveinsyncassignment.database.model.Product;
import com.assignment.moveinsyncassignment.database.model.ProductCategory;
import com.assignment.moveinsyncassignment.database.model.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;
    Retrofit retrofit;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        products = new LinkedList<>();
        callApi();

//        products.add(Products.ITEM_1);
//        products.add(Products.ITEM_2);
//        products.add(Products.ITEM_3);
//        products.add(Products.ITEM_4);
//        products.add(Products.ITEM_5);
//        products.add(Products.ITEM_6);


        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(3, "All Body Products"));
        productCategoryList.add(new ProductCategory(4, "Skin Care"));
        productCategoryList.add(new ProductCategory(5, "Hair Care"));
        productCategoryList.add(new ProductCategory(6, "Make Up"));
        productCategoryList.add(new ProductCategory(7, "Fragrance"));

        setProductRecycler(productCategoryList);

       // List<Product> productsList = new ArrayList<>();

        //setProdItemRecycler(productsList);

    }

    private void callApi() {
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

       // ApiInterface apiInterface = retrofit.create(ApiInterface.class);

      //  Call<List<Product>> response = apiInterface.getProducts();

//        response.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });

        callEndPoints();
    }

    private void setRecyclerView() {
        if(products != null){
            productAdapter = new ProductAdapter(mContext,products);
            recyclerView = findViewById(R.id.product_recycler);
            layoutManager = new GridLayoutManager(MainActivity.this,2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(productAdapter);
            productAdapter.notifyDataSetChanged();
            initListener();
        }
    }

    private void initListener(){

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(isNetworkAvailable(mContext)){
                    Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
//                    intent2 = new Intent(AllBreedsActivity.this, BreedDetailActivity.class);
//                    if(allBreedsData != null){
//                        intent2.putExtra("breedType", allBreedsData.get(position));
//                        selectedBreed = allBreedsData.get(position);
//                    }
//                    flag = 0;
//                    callApiForImages();
                }else{
                    Toast.makeText(MainActivity.this, "Not connected to Internet.", Toast.LENGTH_SHORT).show();
                    //showToast("Not connected to Internet.");
                }
            }
        });
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void callEndPoints() {

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Observable<List<Product>> observable = apiInterface.getProducts();
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<List<Product>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(MainActivity.this, "onSubscribe", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(List<Product> productsList) {
                products.addAll(productsList);
                Toast.makeText(MainActivity.this, "onNext"+products.get(0).getName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {
                setRecyclerView();
                Toast.makeText(MainActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProductRecycler(List<ProductCategory> productCategoryList){

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);

    }

    private void setProdItemRecycler(List<Product> productsList){

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);

    }
}
