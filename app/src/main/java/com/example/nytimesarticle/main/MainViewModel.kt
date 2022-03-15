package com.example.nytimesarticle.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimesarticle.data.models.Content
import com.example.nytimesarticle.data.models.NYArticleResponse
import com.example.nytimesarticle.util.DispatcherProvider
import com.example.nytimesarticle.util.Resource
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {

    sealed class NYArticleEvent {
        class Success(val contents: ArrayList<Content>): NYArticleEvent()
        class Failure(val errorText: String): NYArticleEvent()
        object Loading : NYArticleEvent()
        object Empty : NYArticleEvent()
    }

    private val _conversion = MutableStateFlow<NYArticleEvent>(NYArticleEvent.Empty)
    val conversion: StateFlow<NYArticleEvent> = _conversion

    fun getArticles(access_key: String) {
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = NYArticleEvent.Loading
            when(val ratesResponse = repository.getNYArticles(access_key)) {
                is Resource.Error -> _conversion.value = NYArticleEvent.Failure(ratesResponse.message!!)
                is Resource.Success -> {
                    val contents: ArrayList<Content> = ratesResponse.data!!.contents
                    if(contents.isEmpty()) {
                        _conversion.value = NYArticleEvent.Failure("Unexpected error")
                    } else {
                        _conversion.value = NYArticleEvent.Success(contents)
                    }
                }
            }
        }
    }

}