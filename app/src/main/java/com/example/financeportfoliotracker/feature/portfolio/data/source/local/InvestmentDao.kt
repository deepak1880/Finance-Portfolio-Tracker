package com.example.financeportfoliotracker.feature.portfolio.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InvestmentDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertInvestment(investment: InvestmentEntity)

    @Update
    suspend fun updateInvestment(investment: InvestmentEntity)

    @Delete
    suspend fun deleteInvestment(investment: InvestmentEntity)

    @Query("SELECT * FROM investments")
    fun getAllInvestments(): Flow<List<InvestmentEntity>>

    @Query("SELECT * FROM investments WHERE investmentId = :id LIMIT 1")
    suspend fun getInvestmentById(id: Int): InvestmentEntity

}