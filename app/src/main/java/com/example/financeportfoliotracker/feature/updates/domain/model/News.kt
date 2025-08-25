package com.example.financeportfoliotracker.feature.updates.domain.model

data class News(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)