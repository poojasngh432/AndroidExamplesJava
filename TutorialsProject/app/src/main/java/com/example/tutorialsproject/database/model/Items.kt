package com.example.tutorialsproject.database.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Items(

        @field:SerializedName("repo")
        val repoName: String,

        @field:SerializedName("repo_link")
        val repoLink: String? = null,
        @field:SerializedName("desc")
        val desc: String? = null,
        @field:SerializedName("lang")
        val lang: String? = null,
        @field:SerializedName("stars")
        val stars: String? = null,
        @field:SerializedName("forks")
        val forks: String? = null,
        @field:SerializedName("added_stars")
        val addedStars: String? = null,
        @field:SerializedName("avatars")
        val avatars: List<String>? = null) : Serializable {


    override fun toString() = repoName

}