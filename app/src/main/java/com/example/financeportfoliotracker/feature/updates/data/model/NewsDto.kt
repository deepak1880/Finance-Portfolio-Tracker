package com.example.financeportfoliotracker.feature.updates.data.model

import com.example.financeportfoliotracker.feature.updates.domain.model.News

data class NewsDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    fun toDomain(): News {
        return News(userId, id, title, body)
    }
}
