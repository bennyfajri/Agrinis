package com.agrinis.app.data.models

import com.agrinis.app.R
import javax.inject.Inject


class ObjectCategory @Inject constructor(){
    private val listName = listOf(
        "business",
        "entertainment",
        "general",
        "health",
        "science",
        "sports",
        "technology"
    )

    private val listIcon = listOf(
        R.drawable.ic_business,
        R.drawable.ic_entertainment,
        R.drawable.ic_general,
        R.drawable.ic_health,
        R.drawable.ic_science,
        R.drawable.ic_sports,
        R.drawable.ic_technology,
    )

    private val listBgColor = listOf(
        "#ffe0b2",
        "#f8bbd0",
        "#b2ebf2",
        "#b2dfdb",
        "#e1bee7",
        "#f0f4c3",
        "#d7ccc8"
    )

    val listCategory: ArrayList<Category>
        get() {
            val list = arrayListOf<Category>()
            for (i in listName.indices){
                val category = Category()
                category.name = listName[i]
                category.icon = listIcon[i]
                category.backgroundColor = listBgColor[i]
                list.add(category)
            }
            return list
        }
}