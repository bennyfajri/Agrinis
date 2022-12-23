package com.agrinis.app.di

import android.content.Context
import androidx.room.Room
import com.agrinis.app.BuildConfig
import com.agrinis.app.di.persistence.AppDatabase
import com.agrinis.app.network.ApiService
import com.agrinis.app.repository.article.ArticleRepository
import com.agrinis.app.repository.article.ArticleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(api: ApiService, db: AppDatabase): ArticleRepository{
        return ArticleRepositoryImpl(api, db)
    }
    
    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "db_news"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideArticleDao(database: AppDatabase) =
        database.articleDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(db: AppDatabase) = db.remoteKeysDao()
 }