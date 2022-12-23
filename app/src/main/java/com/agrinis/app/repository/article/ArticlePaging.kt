package com.agrinis.app.repository.article

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.network.ApiService
import javax.inject.Inject

class ArticlePaging @Inject constructor(
    private val type: String,
    private val query: String? = null,
    private val apiService: ApiService
) : PagingSource<Int, Article>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val TOP_NEWS = "TOP_NEWS"
        const val SEARCH_NEWS = "SEARCH_NEWS"
        const val BY_SOURCE = "BY_SOURCE_NEWS"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = when (type) {
                TOP_NEWS -> {
                    apiService.getTopNews(query as String, position, params.loadSize).articles
                }
                SEARCH_NEWS -> {
                    apiService.getArticles(query = query, position, params.loadSize).articles
                }
                BY_SOURCE -> {
                    apiService.getArticleBySource(
                        sources = query as String,
                        position,
                        params.loadSize
                    ).articles
                }
                else -> {
                    apiService.getTopNews(query as String, position, params.loadSize).articles
                }
            }

            LoadResult.Page(
                data = responseData as List<Article>,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}