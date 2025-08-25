package com.example.financeportfoliotracker.core.di

import com.example.financeportfoliotracker.feature.investmentdetails.data.repository.InvestmentDetailRepositoryImpl
import com.example.financeportfoliotracker.feature.investmentdetails.domain.repository.InvestmentDetailRepository
import com.example.financeportfoliotracker.feature.portfolio.data.repository.PortFolioRepositoryImpl
import com.example.financeportfoliotracker.feature.portfolio.domain.repository.PortFolioRepository
import com.example.financeportfoliotracker.feature.updates.data.repository.NewsRepositoryImpl
import com.example.financeportfoliotracker.feature.updates.domain.repository.NewsRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    abstract fun bindPortFolioRepository(
        impl: PortFolioRepositoryImpl
    ): PortFolioRepository


    @Singleton
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

    @Singleton
    abstract fun bindInvestmentDetailRepository(
        impl: InvestmentDetailRepositoryImpl
    ): InvestmentDetailRepository
}