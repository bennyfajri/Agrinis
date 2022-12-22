package com.agrinis.app.data.models.response

import com.agrinis.app.di.persistence.entities.Article
import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("articles")
    val articles: List<Article>? = null,

    @field:SerializedName("totalResults")
    val totalResuls: Int? = null
)
