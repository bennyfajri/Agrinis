package com.agrinis.app.repository.article

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.agrinis.app.di.persistence.AppDatabase
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.di.persistence.entities.RemoteKeys
import com.agrinis.app.network.ApiService

@ExperimentalPagingApi
class ArticleRemoteMediator(
    private val type: String,
    private val query: String? = null,
    private val apiService: ApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, Article>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val TOP_NEWS = "TOP_NEWS"
        const val SEARCH_NEWS = "SEARCH_NEWS"
        const val BY_SOURCE = "BY_SOURCE_NEWS"
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                nextKey
            }
        }

        return try {
            val responseData = when(type){
                TOP_NEWS -> {
                    apiService.getTopNews(
                        query as String,
                        page = page,
                        size = state.config.pageSize
                    ).articles
                }
                SEARCH_NEWS -> {
                    apiService.getArticles(
                        query,
                        page = page,
                        size = state.config.pageSize
                    ).articles
                }
                BY_SOURCE -> {
                    apiService.getArticleBySource(
                        query as String,
                        page = page,
                        size = state.config.pageSize
                    ).articles
                }
                else -> {
                    apiService.getTopNews(
                        query as String,
                        page = page,
                        size = state.config.pageSize
                    ).articles
                }
            }

            Log.d("Response:::", "load: $responseData")

            val endOfPaginationReached = responseData?.isEmpty()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().deleteRemoteKeys()
                    db.articleDao().deleteAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached == true) null else page + 1
                val keys = responseData?.map {
                    RemoteKeys(id = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                keys?.let { db.remoteKeysDao().insertAll(it) }
                responseData?.let { db.articleDao().insertArticle(it) }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached == true)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Article>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            db.remoteKeysDao().getRemoteKeysId(data.id.toString())
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Article>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            db.remoteKeysDao().getRemoteKeysId(data.id.toString())
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Article>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                db.remoteKeysDao().getRemoteKeysId(id.toString())
            }
        }
    }
}