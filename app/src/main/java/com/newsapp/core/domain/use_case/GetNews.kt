package com.newsapp.core.domain.use_case

import com.newsapp.core.data.repository.Interface.INewsRepository
import com.newsapp.core.domain.model.News
import com.newsapp.core.util.Resource
import kotlinx.coroutines.flow.Flow


class GetNews (
    private val repository: INewsRepository
)  {

    val TAG = "GetNewsUseCase"
    operator  fun invoke(): Flow<Resource<News>> {
        val result = repository.getAll()
        return result
    }
}