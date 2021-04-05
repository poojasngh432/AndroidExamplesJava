package com.example.tutorialsproject.database.model

import com.google.gson.annotations.SerializedName

data class Repo(
        @field:SerializedName("count")
        val count: Int? = null,
        @field:SerializedName("msg")
        val msg: String? = null,
        @field:SerializedName("items")
        val items: List<Items>? = null
)