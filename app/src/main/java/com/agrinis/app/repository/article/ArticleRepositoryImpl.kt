package com.agrinis.app.repository.article

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.agrinis.app.di.persistence.AppDatabase
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Created by Arca International on 21/11/2022
 */
class ArticleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase
) : ArticleRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getArticle(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = ArticleRemoteMediator(apiService, db),
            pagingSourceFactory = {
                db.articleDao().getAllArticle()
            }
        ).flow
    }
}