package com.example.financeportfoliotracker.feature.investmentdetails.domain.usecase

import com.example.financeportfoliotracker.feature.investmentdetails.domain.repository.InvestmentDetailRepository
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import javax.inject.Inject

class GetInvestmentByIdUseCase @Inject constructor(
    private val repository: InvestmentDetailRepository
) {
    suspend operator fun invoke(id: Int): InvestmentEntity {
        return repository.getInvestmentById(id)
    }
}