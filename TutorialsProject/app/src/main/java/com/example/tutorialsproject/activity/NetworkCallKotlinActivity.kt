package com.example.tutorialsproject.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorialsproject.R
import com.example.tutorialsproject.database.model.Repo
import com.example.tutorialsproject.database.remote.ApiService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkCallKotlinActivity : AppCompatActivity() {
    private val TAG: String = javaClass.getName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_call_kotlin)

        apiCall()

    }

    private fun apiCall() {
        val  retrofit = Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val trendingGithubService = retrofit.create(ApiService::class.java)
        trendingGithubService.getTrendingRepos("java", "weekly")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Repo> {
                    override fun onSuccess(t: Repo) {
                   //     Log.d(TAG, "repositoriesSingle::onSuccess")
//                        val data = t.items
//                        data?.get(0)?.repoName?.let { Log.d(TAG, it) }
//                        val list = data?.toMutableList()

//                        val nameObserver = Observer<GithubEntity> { data ->
//
//                        }

                    }

                    override fun onError(e: Throwable) {
//                        hideProgressBar()
//                        Log.d(TAG, "repositoriesSingle::onError")
//                        Toast.makeText(
//                                this@MainActivity,
//                                "Error connecting to GitHub",
//                                Toast.LENGTH_SHORT
//                        ).show()
                    }

                    override fun onSubscribe(d: Disposable) {
                      //  showProgressBar()
                        Log.d(TAG, "repositoriesSingle::onSubscribe")
                    }
                })
    }

}