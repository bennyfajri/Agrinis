package com.agrinis.app.di

import com.agrinis.app.BuildConfig
import com.agrinis.app.network.interceptor.NoConnectionInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Created by Arca International on 21/11/2022
 */
val networkModules = module {
    val baseUrl = BuildConfig.BASE_URL

    singleOf(::NoConnectionInterceptor)
    singleOf(::provideHttpLoggingInterceptor)
    singleOf(::provideHttpClient)

    // TODO: declare service as singleton
}

private fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, connectionInterceptor: NoConnectionInterceptor): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.connectTimeout(60, TimeUnit.SECONDS)
    client.writeTimeout(60, TimeUnit.SECONDS)
    client.readTimeout(60, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) client.addInterceptor(loggingInterceptor)
    client.addInterceptor(connectionInterceptor)

    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("Accept", "application/json")
        // TODO: use NEWS_API_KEY here
        val request = requestBuilder
            .method(original.method, original.body)
            .build()
        return@addInterceptor it.proceed(request)
    }.build()
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

/**
 * create service use retrofit
 */
inline fun <reified T> createService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    return retrofit.create(T::class.java)
}