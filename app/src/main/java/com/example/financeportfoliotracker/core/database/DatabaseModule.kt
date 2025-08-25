package com.example.financeportfoliotracker.core.database

import android.content.Context
import androidx.room.Room
import com.example.financeportfoliotracker.feature.portfolio.data.source.local.FinancePortfolioDatabase
import com.example.financeportfoliotracker.feature.portfolio.data.source.local.InvestmentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FinancePortfolioDatabase {
        return Room.databaseBuilder(
            context = context,
            FinancePortfolioDatabase::class.java,
            "finance_portfolio_db"
        ).build()
    }

    @Provides
    fun provideInvestmentDao(financePortfolioDatabase: FinancePortfolioDatabase): InvestmentDao {
        return financePortfolioDatabase.investmentDao()
    }
}