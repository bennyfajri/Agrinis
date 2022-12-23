package com.agrinis.app.repository.article

import androidx.paging.PagingData
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.network.Resource
import dagger.Provides
import kotlinx.coroutines.flow.Flow

/**
 * @author Created by Arca International on 21/11/2022
 */
interface ArticleRepository {
    fun getArticle(query: String? = null): Flow<PagingData<Article>>
    fun getTopNews(country: String? = null): Flow<PagingData<Article>>
}