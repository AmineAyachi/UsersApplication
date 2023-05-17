package com.newsapp.core.data.repository.Interface

import com.newsapp.core.domain.model.News
import com.newsapp.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getAll (): Flow<Resource<News>>
}