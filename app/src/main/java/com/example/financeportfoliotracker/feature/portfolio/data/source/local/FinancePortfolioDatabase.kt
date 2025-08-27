package com.example.financeportfoliotracker.feature.portfolio.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financeportfoliotracker.feature.portfolio.data.source.local.InvestmentDao
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity

@Database(entities = [InvestmentEntity::class], version = 2, exportSchema = false)
abstract class FinancePortfolioDatabase : RoomDatabase() {
    abstract fun investmentDao(): InvestmentDao
}