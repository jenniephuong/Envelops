package com.example.envelops.screens

enum class Category {
    Transport,
    Food,
    Social
}

fun getCategoryList(): List<Category> {
    val categoryList = mutableListOf<Category>()
    categoryList.add(Category.Transport)
    categoryList.add(Category.Food)
    categoryList.add(Category.Social)

    return categoryList
}