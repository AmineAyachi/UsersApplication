package com.newsapp.feature_news.presentation.viewmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.newsapp.core.domain.model.Article


data class NewsState(
    var News: List<Article> = emptyList(),
    var isloading:Boolean= false,
    var Error:Boolean= false,
)