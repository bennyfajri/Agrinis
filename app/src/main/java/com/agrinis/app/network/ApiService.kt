package com.agrinis.app.network

import com.agrinis.app.BuildConfig
import com.agrinis.app.data.models.response.ServerResponse
import com.agrinis.app.di.persistence.entities.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Created by Arca International on 21/11/2022
 */
interface ApiService {

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : ServerResponse

    @GET("top-headlines")
    suspend fun getArticleBySource(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("pageSize") size: Int,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : ServerResponse

    @GET("everything")
    suspend fun getArticles(
        @Query("q") query: String? = null,
        @Query("page") page: Int,
        @Query("pageSize") size: Int,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : ServerResponse

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") size: Int,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : ServerResponse
}