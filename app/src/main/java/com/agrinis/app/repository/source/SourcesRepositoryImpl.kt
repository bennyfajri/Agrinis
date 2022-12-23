package com.agrinis.app.repository.source

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.agrinis.app.data.models.response.ServerResponse
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.network.ApiService
import com.agrinis.app.network.Result
import com.agrinis.app.repository.article.ArticlePaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SourceRepository {

    companion object {
        const val STATUS_OK = "ok"
        const val TAG = "Response::::::"
    }

    override fun getSource(category: String): Flow<Result<ServerResponse>> =
        flow<Result<ServerResponse>> {
            emit(Result.Loading())
            val response = apiService.getSources(category)
            response.let {
                if (it.status == STATUS_OK) emit(Result.Success(it))
                else emit(Result.Error(it.status))
            }
        }.catch {
            Log.d(TAG, "getSources: ${it.message}")
            emit(Result.Error(it.message.toString()))
        }

    override fun getArticleBySource(source: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ArticlePaging(ArticlePaging.BY_SOURCE, source, apiService)
            }
        ).flow
    }

    override fun getArticle(query: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ArticlePaging(ArticlePaging.SEARCH_NEWS, query, apiService)
            }
        ).flow
    }
}