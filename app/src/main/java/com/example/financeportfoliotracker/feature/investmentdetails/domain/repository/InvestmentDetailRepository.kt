package com.example.financeportfoliotracker.feature.investmentdetails.domain.repository

import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity

interface InvestmentDetailRepository {
    suspend fun getAllInvestmentDetails(): List<InvestmentEntity>
    suspend fun insertInvestmentDetail(detail: InvestmentEntity)
    suspend fun updateInvestmentDetail(detail: InvestmentEntity)
}