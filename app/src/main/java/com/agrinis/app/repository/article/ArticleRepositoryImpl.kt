package com.agrinis.app.repository.article

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.agrinis.app.di.persistence.AppDatabase
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.network.ApiService
import com.agrinis.app.repository.article.ArticlePaging.Companion.SEARCH_NEWS
import com.agrinis.app.repository.article.ArticlePaging.Companion.TOP_NEWS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Created by Arca International on 21/11/2022
 */
class ArticleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase
) : ArticleRepository {

    override fun getArticle(query: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ArticlePaging(SEARCH_NEWS, query, apiService)
            }
        ).flow
    }

    override fun getTopNews(country: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ArticlePaging(TOP_NEWS, country, apiService)
            }
        ).flow
    }
}