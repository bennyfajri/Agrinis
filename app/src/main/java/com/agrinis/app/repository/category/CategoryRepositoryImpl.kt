package com.agrinis.app.repository.category

import com.agrinis.app.data.models.Category
import com.agrinis.app.data.models.ObjectCategory
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val category: ObjectCategory
) : CategoryRepository {
    override fun getCategory(): List<Category> = category.listCategory
}