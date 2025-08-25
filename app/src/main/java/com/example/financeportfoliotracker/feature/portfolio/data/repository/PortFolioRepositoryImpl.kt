package com.example.financeportfoliotracker.feature.portfolio.data.repository

import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import com.example.financeportfoliotracker.feature.portfolio.data.source.local.InvestmentDao
import com.example.financeportfoliotracker.feature.portfolio.domain.repository.PortFolioRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.first

@Singleton
class PortFolioRepositoryImpl @Inject constructor(
    private val dao: InvestmentDao
) : PortFolioRepository {

    override suspend fun getAllInvestmentsList(): List<InvestmentEntity> {
        return dao.getAllInvestments().first()
    }
}