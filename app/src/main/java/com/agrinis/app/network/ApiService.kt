package com.agrinis.app.network

import com.agrinis.app.data.models.response.FooResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Created by Arca International on 21/11/2022
 */
interface ApiService {

    @GET("/foo")
    suspend fun foo(): Response<FooResponse>
}