package com.assignment.recipeapp

import android.database.Observable

interface RecipeApi {

    @GET("/api/search")
    fun search(@Query("q") query: String): Observable<SearchResponse>

    @GET("/api/get")
    operator fun get(@Query("rId") id: String): Observable<GetResponse>
}