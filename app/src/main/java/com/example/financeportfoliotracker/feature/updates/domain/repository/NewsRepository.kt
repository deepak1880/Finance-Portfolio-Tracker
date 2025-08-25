package com.example.financeportfoliotracker.feature.updates.domain.repository

import com.example.financeportfoliotracker.feature.updates.domain.model.News

interface NewsRepository {
    suspend fun getPosts(): List<News>
}
