package com.agrinis.app.repository.category

import com.agrinis.app.data.models.Category

interface CategoryRepository {
    fun getCategory(): List<Category>
}