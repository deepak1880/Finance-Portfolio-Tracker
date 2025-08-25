package com.example.financeportfoliotracker.core.networks

import com.example.financeportfoliotracker.feature.updates.data.model.NewsDto
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<NewsDto>
}
