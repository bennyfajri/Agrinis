package com.agrinis.app.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.agrinis.app.di.persistence.entities.Article
import com.agrinis.app.repository.article.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    fun getArticle(query: String? = null) =
        repository.getArticle(query).cachedIn(viewModelScope).asLiveData()

    fun getTopNews(
        query: String
    ) = repository.getTopNews(query).cachedIn(viewModelScope).asLiveData()

}