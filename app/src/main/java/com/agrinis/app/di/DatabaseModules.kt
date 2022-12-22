package com.agrinis.app.di

import android.app.Application
import androidx.room.Room
import com.agrinis.app.di.persistence.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModules = module {
    // TODO: declare local database and DAO here

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app.database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    single { provideDatabase(androidApplication()) }
}