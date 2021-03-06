package com.assignment.pristyncareproject.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.assignment.pristyncareproject.R;
import com.assignment.pristyncareproject.data.Interface.ApiInterface;
import com.assignment.pristyncareproject.data.api.RetrofitService;
import com.assignment.pristyncareproject.data.model.Photo;
import com.assignment.pristyncareproject.data.model.Photos;
import com.assignment.pristyncareproject.view.adapter.MainAdapter;
import com.assignment.pristyncareproject.viewmodel.PhotoViewModel;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
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

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Context mContext;
    public static MutableLiveData<List<Photo>> photosLiveData;
    public static List<Photo> photosData;
    private List<String> photosList;
    private MainAdapter mainAdapter;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager2;
    GridLayoutManager gridLayoutManager3;
    RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "MainActivity";
    private static final String FIRST_PAGE = "1";
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isScrolling = false;
    private int lazyLoadPageNumber = 1;
    private int totalPages = 0;
    private int layout = 1;
    private PhotoViewModel photoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        photosList = new LinkedList<>();
        photosData = new LinkedList<>();
        photosLiveData = new MutableLiveData<List<Photo>>();
        progressBar = findViewById(R.id.progress_load_more);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        gridLayoutManager2 = new GridLayoutManager(this, 2);
        gridLayoutManager3 = new GridLayoutManager(this, 3);

        changeLayoutManager();
        setRecyclerView();

        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        photoViewModel.getAllPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                mainAdapter.notifyDataSetChanged();
                runOnUiThread(() -> {
                    swipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);
                    setRecyclerView();
                });
            }
        });

        if(isNetworkAvailable(this)){
            getPhotosData();
            swipeRefreshLayout.setOnRefreshListener(() -> getPhotosDataFromApi(FIRST_PAGE));
        }else{
            showToast("Not connected to Internet");
        }

    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(this, photosList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
        initListener();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItems = 0, totalItems = 0, scrolledOutItems = -1;
                if(layoutManager == linearLayoutManager){
                    currentItems = linearLayoutManager.getChildCount();
                    totalItems = linearLayoutManager.getItemCount();
                    scrolledOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                }else{
                    if(layoutManager == gridLayoutManager2){
                        currentItems = gridLayoutManager2.getChildCount();
                        totalItems = gridLayoutManager2.getItemCount();
                        scrolledOutItems = gridLayoutManager2.findFirstVisibleItemPosition();
                    }else if(layoutManager == gridLayoutManager3){
                        currentItems = gridLayoutManager3.getChildCount();
                        totalItems = gridLayoutManager3.getItemCount();
                        scrolledOutItems = gridLayoutManager3.findFirstVisibleItemPosition();
                    }
                }

                Log.d(TAG, "onScrolled: scrolledOutItems: " + scrolledOutItems);
                Log.d(TAG, "onScrolled: totalItems: " + totalItems);
                Log.d(TAG, "onScrolled: currentItems: " + currentItems);

                if (totalPages > 0) {
                    if (lazyLoadPageNumber <= totalPages) {
                        if (isScrolling && (totalItems == currentItems + scrolledOutItems)) {
                            // Load new data
                            lazyLoadPageNumber++;
                            AsyncTask.execute(() -> getPhotosDataFromApi(valueOf(lazyLoadPageNumber)));
                        }
                    } else {

                    }
                }
            }
        });
    }

    private void initListener() {
        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isNetworkAvailable(mContext)) {
                    Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                    intent.putExtra("url", photosList.get(position));
                    startActivity(intent);
                } else {
                    showToast("Not connected to Internet.");
                }
            }
        });
    }

    private void changeLayoutManager() {
        if(layout == 1){
            layout = 2;
            layoutManager = linearLayoutManager;
        }else if(layout == 2){
            layout = 3;
            layoutManager = gridLayoutManager2;
        }else if(layout == 3){
            layout = 1;
            layoutManager = gridLayoutManager3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_change_recycler_manager:
                changeLayoutManager();
                setRecyclerView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getPhotosData() {
        if (isNetworkAvailable(mContext)) {
            AsyncTask.execute(() -> getPhotosDataFromApi(FIRST_PAGE));
        } else {
            showToast("Not connected to Internet.");
        }
    }

    private void getPhotosDataFromApi(String pageNumber) {
        if (("1").equals(pageNumber)) runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));
        ApiInterface apiService = RetrofitService.getInstance().create(ApiInterface.class);
        Call<Photos> call = apiService.getPhotosList(pageNumber);
        call.enqueue(new Callback<Photos>() {

            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                Log.d("Response: ", valueOf(response.body()));

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (("1").equals(pageNumber)) {
                            lazyLoadPageNumber = 1;
                            photosData.clear();
                        }
                        List<Photo> data = response.body().getPhotos().getPhoto();
                        photoViewModel.addPhotos(data);

                        photosList.clear();
                        for(Photo photo: photoViewModel.getAllPhotos().getValue()){
                            //https://live.staticflickr.com/{server-id}/{id}_{secret}.jpg
                            String url = "https://live.staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
                            photosList.add(url);
                        }
                        totalPages = response.body().getPhotos().getPages();

                    } else {
                        runOnUiThread(() -> {
                            photosList.clear();
                            photosData.clear();
                            swipeRefreshLayout.setRefreshing(false);
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                } else {
                    runOnUiThread(() -> {
                        photosList.clear();
                        photosData.clear();
                        swipeRefreshLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Something is wrong!", Toast.LENGTH_SHORT).show();
                    });

                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                runOnUiThread(() -> {
                    photosList.clear();
                    photosData.clear();
                    swipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

}
