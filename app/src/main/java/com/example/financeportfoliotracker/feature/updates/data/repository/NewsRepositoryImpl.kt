package com.example.financeportfoliotracker.feature.updates.data.repository

import com.example.financeportfoliotracker.core.networks.ApiService
import com.example.financeportfoliotracker.feature.updates.domain.model.News
import com.example.financeportfoliotracker.feature.updates.domain.repository.NewsRepository
import javax.inject.Inject  // <-- use javax, not jakarta
import javax.inject.Singleton
import kotlin.collections.map

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NewsRepository {

    override suspend fun getPosts(): List<News> {
        return apiService.getPosts().map { it.toDomain() }
    }
}
