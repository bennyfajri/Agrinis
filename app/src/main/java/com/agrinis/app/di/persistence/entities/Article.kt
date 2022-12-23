package com.agrinis.app.di.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Created by Muhamad Jalaludin on 21/11/2022
 */
@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("urlToImage")
    val urlToImage: String,

    @field:SerializedName("publishedAt")
    val publishedAt: String,

    @field:SerializedName("content")
    val content: String
)
