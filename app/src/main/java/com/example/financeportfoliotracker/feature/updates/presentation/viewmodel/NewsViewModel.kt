package com.example.financeportfoliotracker.feature.updates.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeportfoliotracker.feature.updates.domain.model.News
import com.example.financeportfoliotracker.feature.updates.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getPostsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _posts = MutableLiveData<List<News>>()
    val posts: LiveData<List<News>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = getPostsUseCase()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
