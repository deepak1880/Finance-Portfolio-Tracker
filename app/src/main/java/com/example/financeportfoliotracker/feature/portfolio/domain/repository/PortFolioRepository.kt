package com.example.financeportfoliotracker.feature.portfolio.domain.repository

import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity

interface PortFolioRepository {
    suspend fun getAllInvestmentsList(): List<InvestmentEntity>
    suspend fun deleteInvestment(investment: InvestmentEntity)
}
