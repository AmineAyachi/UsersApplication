package com.newsapp.core.domain.model

data class News(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)
