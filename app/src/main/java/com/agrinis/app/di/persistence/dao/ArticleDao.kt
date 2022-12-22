package com.agrinis.app.di.persistence.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agrinis.app.di.persistence.entities.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: List<Article>)

    @Query("SELECT * FROM article")
    fun getAllArticle(): PagingSource<Int, Article>

    @Query("DELETE FROM article")
    suspend fun deleteAll()
}