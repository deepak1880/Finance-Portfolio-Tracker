package com.example.financeportfoliotracker.feature.investmentdetails.data.repository

import com.example.financeportfoliotracker.feature.investmentdetails.domain.repository.InvestmentDetailRepository
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import com.example.financeportfoliotracker.feature.portfolio.data.source.local.InvestmentDao
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.first

@Singleton
class InvestmentDetailRepositoryImpl @Inject constructor(
    private val dao: InvestmentDao
) : InvestmentDetailRepository {

    override suspend fun getAllInvestmentDetails(): List<InvestmentEntity> =
        dao.getAllInvestments().first()


    override suspend fun insertInvestmentDetail(detail: InvestmentEntity) =
        dao.insertInvestment(detail)

    override suspend fun updateInvestmentDetail(detail: InvestmentEntity) =
        dao.updateInvestment(detail)
}