package com.agrinis.app.di.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agrinis.app.di.persistence.dao.ArticleDao
import com.agrinis.app.di.persistence.dao.RemoteKeysDao
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.di.persistence.entities.RemoteKeys

/**
 * @author Created by Arca International on 21/11/2022
 */

// TODO: insert entity to database entities below
@Database(
    entities = [Article::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    // TODO: declare DAO here
    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}