package com.example.financeportfoliotracker.feature.investmentdetails.presentation.ui

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.financeportfoliotracker.core.ui.base.BaseFragment
import com.example.financeportfoliotracker.databinding.FragmentInvestmentDetailsBinding
import com.example.financeportfoliotracker.feature.investmentdetails.presentation.viewmodel.InvestmentDetailViewModel
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import java.util.Calendar
import android.widget.ArrayAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvestmentDetailsFragment : BaseFragment<FragmentInvestmentDetailsBinding>() {

    private val viewModel: InvestmentDetailViewModel by viewModels()
    private var selectedDetail: InvestmentEntity? = null

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInvestmentDetailsBinding.inflate(inflater, container, false)

    override fun initialSetUp() {
        super.initialSetUp()

        setupSpinner()
        setupDatePicker()
        observeInvestmentDetails()

        binding.btnSubmit.setOnClickListener {
            handleSubmit()
        }

        viewModel.fetchInvestmentDetails()

    }

    private fun setupSpinner() {
        val funds = listOf("Fund A", "Fund B", "Fund C")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, funds)
        binding.spinnerFund.setAdapter(adapter)
    }

    private fun setupDatePicker() {
        binding.etInvestmentDate.editText?.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    binding.etInvestmentDate.editText?.setText("$dayOfMonth/${month + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun observeInvestmentDetails() {
        viewModel.investmentDetails.observe(viewLifecycleOwner) { list ->
            Log.d("InvestmentDetail", "Current List: $list")
        }
    }

    private fun handleSubmit() {
        val fundName = binding.spinnerFund.text.toString()
        val investorName = binding.etInvestorName.editText?.text.toString()
        val investmentDate = binding.etInvestmentDate.editText?.text.toString()
        val status = when {
            binding.radioActive.isChecked -> "Active"
            binding.radioComplete.isChecked -> "Complete"
            else -> ""
        }

        if (fundName.isEmpty() || investorName.isEmpty() || investmentDate.isEmpty() || status.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val detail = InvestmentEntity(
            investmentId = selectedDetail?.investmentId ?: 0,
            fundName = fundName,
            investmentName = investorName,
            investmentDate = investmentDate,
            investmentStatus = status,
            investmentAmount = TODO(),
        )

        if (selectedDetail == null) {
            viewModel.insertInvestmentDetail(detail)
            Toast.makeText(requireContext(), "Investment added", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.updateInvestmentDetail(detail)
            Toast.makeText(requireContext(), "Investment updated", Toast.LENGTH_SHORT).show()
        }

        clearForm()
    }

    private fun clearForm() {
        binding.spinnerFund.setText("")
        binding.etInvestorName.editText?.text?.clear()
        binding.etInvestmentDate.editText?.text?.clear()
        binding.radioGroupStatus.clearCheck()
        selectedDetail = null
    }

    override fun setUpOnClickListeners() {
        super.setUpOnClickListeners()
    }
}