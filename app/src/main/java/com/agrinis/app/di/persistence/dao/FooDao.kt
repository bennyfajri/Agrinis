package com.agrinis.app.di.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.agrinis.app.di.persistence.entities.Foo

/**
 * @author Created by Arca International on 21/11/2022
 */
@Dao
interface FooDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Foo)
}