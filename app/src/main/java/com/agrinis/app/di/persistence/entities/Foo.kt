package com.agrinis.app.di.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Created by Muhamad Jalaludin on 21/11/2022
 */
@Entity(tableName = "foo")
data class Foo(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "name") val name: String
)
