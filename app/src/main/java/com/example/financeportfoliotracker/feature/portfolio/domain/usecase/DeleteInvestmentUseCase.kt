package com.example.financeportfoliotracker.feature.portfolio.domain.usecase

import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import com.example.financeportfoliotracker.feature.portfolio.domain.repository.PortFolioRepository
import jakarta.inject.Inject


class DeleteInvestmentUseCase @Inject constructor(
    private val repository: PortFolioRepository
) {
    suspend operator fun invoke(investment: InvestmentEntity) {
        repository.deleteInvestment(investment)
    }
}