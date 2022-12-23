package com.agrinis.app.ui.category

import androidx.lifecycle.ViewModel
import com.agrinis.app.repository.category.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {
    fun getCategory() = repository.getCategory()
}