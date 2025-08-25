package com.example.financeportfoliotracker.feature.investmentdetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeportfoliotracker.feature.investmentdetails.domain.usecase.GetInvestmentDetailsUseCase
import com.example.financeportfoliotracker.feature.investmentdetails.domain.usecase.InsertInvestmentDetailUseCase
import com.example.financeportfoliotracker.feature.investmentdetails.domain.usecase.UpdateInvestmentDetailUseCase
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class InvestmentDetailViewModel @Inject constructor(
    private val getUseCase: GetInvestmentDetailsUseCase,
    private val insertUseCase: InsertInvestmentDetailUseCase,
    private val updateUseCase: UpdateInvestmentDetailUseCase
) : ViewModel() {

    private val _investmentDetails = MutableLiveData<List<InvestmentEntity>>()
    val investmentDetails: LiveData<List<InvestmentEntity>> = _investmentDetails

    fun fetchInvestmentDetails() {
        viewModelScope.launch {
            _investmentDetails.value = getUseCase()
        }
    }

    fun insertInvestmentDetail(detail: InvestmentEntity) {
        viewModelScope.launch {
            insertUseCase(detail)
            fetchInvestmentDetails()
        }
    }

    fun updateInvestmentDetail(detail: InvestmentEntity) {
        viewModelScope.launch {
            updateUseCase(detail)
            fetchInvestmentDetails()
        }
    }
}