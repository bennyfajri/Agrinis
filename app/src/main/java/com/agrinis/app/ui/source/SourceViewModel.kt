package com.agrinis.app.ui.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.agrinis.app.repository.source.SourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val repository: SourceRepository
) : ViewModel() {
    fun getSource(category: String)= repository.getSource(category).asLiveData()
    fun getArticleBySource(source: String) = repository.getArticleBySource(source).asLiveData()
}