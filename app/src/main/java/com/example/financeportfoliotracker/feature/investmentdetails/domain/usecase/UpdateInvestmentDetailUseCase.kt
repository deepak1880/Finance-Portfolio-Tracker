package com.example.financeportfoliotracker.feature.investmentdetails.domain.usecase

import com.example.financeportfoliotracker.feature.investmentdetails.domain.repository.InvestmentDetailRepository
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import jakarta.inject.Inject

class UpdateInvestmentDetailUseCase @Inject constructor(
    private val repository: InvestmentDetailRepository
) {
    suspend operator fun invoke(detail: InvestmentEntity) =
        repository.updateInvestmentDetail(detail)
}