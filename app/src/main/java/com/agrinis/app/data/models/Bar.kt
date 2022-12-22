package com.agrinis.app.data.models

import com.squareup.moshi.Json

/**
 * @author Created by Muhamad Jalaludin on 21/11/2022
 */
data class Bar(
    @Json(name = "name")
    val name: String
)
