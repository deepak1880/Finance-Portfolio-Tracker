package com.example.financeportfoliotracker.feature.portfolio.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import com.example.financeportfoliotracker.feature.portfolio.domain.usecase.GetInvestmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PortFolioViewModel @Inject constructor(
    private val getAllInvestmentsUseCase: GetInvestmentsUseCase
) : ViewModel() {

    private val _investments = MutableLiveData<List<InvestmentEntity>>()
    val investments: LiveData<List<InvestmentEntity>> = _investments

    fun fetchInvestments() {
        viewModelScope.launch {
            try {
                val result = getAllInvestmentsUseCase()
                _investments.value = result
            } catch (e: Exception) {
                _investments.value = emptyList()
            }
        }
    }
}
