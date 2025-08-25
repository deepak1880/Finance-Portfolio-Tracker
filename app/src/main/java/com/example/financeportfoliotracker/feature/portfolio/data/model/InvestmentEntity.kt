package com.example.financeportfoliotracker.feature.portfolio.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "investments")
data class InvestmentEntity(
    @PrimaryKey(autoGenerate = true)
    val investmentId: Int = 0,
    val investmentName: String,
    val investmentAmount: Double,
    val investmentStatus: String,
    val investmentDate: String,
    val fundName: String
)