package com.newsapp.core.data.remote.services


import com.newsapp.core.domain.model.News
import com.newsapp.core.util.Constants.Companion.API_KEY
import com.newsapp.core.util.Constants.Companion.country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 2,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<News>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<News>

}