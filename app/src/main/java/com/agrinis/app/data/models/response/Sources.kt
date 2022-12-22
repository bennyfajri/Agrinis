package com.agrinis.app.data.models.response

import com.google.gson.annotations.SerializedName

data class Sources(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("language")
    val language: String,

    @field:SerializedName("country")
    val country: String
)
