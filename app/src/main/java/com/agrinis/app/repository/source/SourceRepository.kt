package com.agrinis.app.repository.source

import androidx.paging.PagingData
import com.agrinis.app.data.models.response.ServerResponse
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.network.Result
import kotlinx.coroutines.flow.Flow

interface SourceRepository {
    fun getSource(category: String): Flow<Result<ServerResponse>>
    fun getArticleBySource(source: String? = null): Flow<PagingData<Article>>
}