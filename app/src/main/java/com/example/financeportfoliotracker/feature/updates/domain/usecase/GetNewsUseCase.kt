package com.example.financeportfoliotracker.feature.updates.domain.usecase

import com.example.financeportfoliotracker.feature.updates.domain.model.News
import com.example.financeportfoliotracker.feature.updates.domain.repository.NewsRepository
import jakarta.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<News> {
        val res = repository.getPosts()
        return res;
    }
}
