package com.agrinis.app.di.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agrinis.app.di.persistence.entities.Foo

/**
 * @author Created by Arca International on 21/11/2022
 */
// TODO: insert entity to database entities below
@Database(entities = [Foo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // TODO: declare DAO here
}