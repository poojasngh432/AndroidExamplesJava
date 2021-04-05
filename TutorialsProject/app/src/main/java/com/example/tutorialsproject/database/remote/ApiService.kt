package com.example.tutorialsproject.database.remote

import com.example.tutorialsproject.database.model.Repo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object{
        const val BASE_URL = "https://trendings.herokuapp.com/"
    }

    @GET("repo")
    fun getTrendingRepos(@Query("lang") lang: String,
                         @Query("since") since: String
    ): Single<Repo>

    @GET("search/repositories")
    fun getRepo(@Query("q") search: String = "trending",
                @Query("sort") sort: String = "stars"
    ): Call<GitResponse>

}