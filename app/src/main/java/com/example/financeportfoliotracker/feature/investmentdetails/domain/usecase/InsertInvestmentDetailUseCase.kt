package com.example.financeportfoliotracker.feature.investmentdetails.domain.usecase

import com.example.financeportfoliotracker.feature.investmentdetails.domain.repository.InvestmentDetailRepository
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import jakarta.inject.Inject

class InsertInvestmentDetailUseCase @Inject constructor(
    private val repository: InvestmentDetailRepository
) {
    suspend operator fun invoke(detail: InvestmentEntity) =
        repository.insertInvestmentDetail(detail)
}