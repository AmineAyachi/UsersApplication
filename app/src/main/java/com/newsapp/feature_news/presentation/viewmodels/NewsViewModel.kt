package com.newsapp.feature_news.presentation.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.core.domain.model.Article
import com.newsapp.core.domain.model.News
import com.newsapp.core.domain.use_case.GetNews
import com.newsapp.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: GetNews
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val list:List<News> = emptyList()
    private val _newsState = mutableStateOf(NewsState())
    var newsState: State<NewsState> = _newsState

    init {
        news()
    }


    fun news() {
        viewModelScope.launch {
            getNews().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _newsState.value =  _newsState.value.copy(
                            News  =  result.data?.articles ?: emptyList<Article>(),
                            isloading = false,
                            Error=false
                        )


                    }
                    is Resource.Error -> {
                        _newsState.value =  _newsState.value.copy(
                            isloading = false,
                            Error=true
                        )
                        _eventFlow.emit(UIEvent.ShowSnackbar(result.message ?: "Unknown error"))
                    }
                    is Resource.Loading -> {
                        _newsState.value =  _newsState.value.copy(
                            isloading = true,
                            Error=false
                        )

                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }

}