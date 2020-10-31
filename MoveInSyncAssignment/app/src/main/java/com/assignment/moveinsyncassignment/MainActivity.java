package com.assignment.moveinsyncassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.assignment.moveinsyncassignment.Interface.ApiInterface;
import com.assignment.moveinsyncassignment.activity.Productdetails;
import com.assignment.moveinsyncassignment.adapter.ProductAdapter;
import com.assignment.moveinsyncassignment.adapter.ProductCategoryAdapter;
import com.assignment.moveinsyncassignment.database.DatabaseHelper;
import com.assignment.moveinsyncassignment.database.dao.AllProductsDao;
import com.assignment.moveinsyncassignment.database.dao.DislikesDao;
import com.assignment.moveinsyncassignment.database.dao.LikesDao;
import com.assignment.moveinsyncassignment.database.model.Product;
import com.assignment.moveinsyncassignment.database.model.ProductCategory;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;
    Retrofit retrofit;
    private Context mContext;
    List<ProductCategory> productCategoryList;
    public static List<Product> allProductsData;
    public static List<String> likedImages = new LinkedList<>();
    public static List<String> dislikedImages = new LinkedList<>();
    public DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

        myDb = new DatabaseHelper(this);
        allProductsData = new LinkedList<>();
        productCategoryList = new ArrayList<>();
        setProductsCategoryList();
        setProductRecycler(productCategoryList);

        allProductsData = new AllProductsDao(this).getProductsList();
        likedImages = new LikesDao(this).getLikesList();
        dislikedImages = new DislikesDao(this).getDislikesList();

        if(allProductsData == null || allProductsData.size() == 0){
            if(isNetworkAvailable(this)){
                callApi();
            }else{
                showToast("Not connected to Internet");
            }
        }else if(allProductsData != null && allProductsData.size() > 0){
            setRecyclerView();
        }else{
            if(isNetworkAvailable(this)){
                callApi();
            }else{
                showToast("Not connected to Internet");
            }
        }

        if(!isNetworkAvailable(this)){
            showToast("Not connected to Internet");
        }
    }

    private void showToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    private void setProductsCategoryList() {
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(3, "Handmade Products"));
        productCategoryList.add(new ProductCategory(4, "Customizable"));
        productCategoryList.add(new ProductCategory(5, "Wood"));
        productCategoryList.add(new ProductCategory(6, "Art"));
        productCategoryList.add(new ProductCategory(7, "Home decor"));
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

        callEndPoints();
    }

    private void setRecyclerView() {
        if(allProductsData != null){
            prodItemRecycler = findViewById(R.id.product_recycler);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
            prodItemRecycler.setLayoutManager(layoutManager);
            productAdapter = new ProductAdapter(mContext,allProductsData);
            prodItemRecycler.setItemAnimator(new DefaultItemAnimator());
            prodItemRecycler.setNestedScrollingEnabled(false);
            prodItemRecycler.setHasFixedSize(true);
            prodItemRecycler.setAdapter(productAdapter);
            productAdapter.notifyDataSetChanged();
            initListener();
        }
    }

    private void initListener(){

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isNetworkAvailable(mContext)) {
                    Intent intent = new Intent(MainActivity.this, Productdetails.class);
                    intent.putExtra("name", allProductsData.get(position).getName());
                    intent.putExtra("desc", allProductsData.get(position).getDescription());
                    intent.putExtra("price", String.valueOf(allProductsData.get(position).getPrice()));
                    intent.putExtra("url", allProductsData.get(position).getUrlString());
                    startActivity(intent);
                } else {
                    showToast("Not connected to Internet.");
                    Intent intent = new Intent(MainActivity.this, Productdetails.class);
                    intent.putExtra("name", allProductsData.get(position).getName());
                    intent.putExtra("desc", allProductsData.get(position).getDescription());
                    intent.putExtra("price", String.valueOf(allProductsData.get(position).getPrice()));
                    intent.putExtra("url", allProductsData.get(position).getUrlString());
                    startActivity(intent);
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

            }

            @Override
            public void onNext(List<Product> productsList) {
                allProductsData.addAll(productsList);
                AllProductsDao allProductsDao = new AllProductsDao(getApplicationContext());
                allProductsDao.insertProductsLocal(allProductsData);
            }

            @Override
            public void onError(Throwable e) {
                showToast(e.getMessage());
            }

            @Override
            public void onComplete() {
                setRecyclerView();
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

}
