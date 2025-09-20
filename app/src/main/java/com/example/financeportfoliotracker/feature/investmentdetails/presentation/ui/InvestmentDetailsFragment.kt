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
import android.os.Bundle
import android.widget.Toast
import java.util.Calendar
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.financeportfoliotracker.core.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvestmentDetailsFragment : BaseFragment<FragmentInvestmentDetailsBinding>() {

    private val viewModel: InvestmentDetailViewModel by viewModels()
    private var selectedDetail: InvestmentEntity? = null
    private var investmentId: Int = 0

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInvestmentDetailsBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        investmentId = arguments?.getInt("investmentId") ?: 0
    }

    override fun initialSetUp() {
        super.initialSetUp()

        setupSpinner()
        setupDatePicker()
        observeInvestmentDetails()
        observeInvestmentPrefilledDetails()

        binding.btnSubmit.text = if (Constants.UPDATE_JOURNEY) "Update" else "Submit"
        viewModel.fetchInvestmentDetails()

        if (Constants.UPDATE_JOURNEY && investmentId != 0) {
            viewModel.getInvestmentById(investmentId)
        }
        binding.btnSubmit.setOnClickListener {
            handleSubmit()
        }

        Constants.UPDATE_JOURNEY = false
    }

    private fun setupSpinner() {
        val funds = listOf(
            "Alpha Growth Fund",
            "BlueChip Equity Fund",
            "Global Opportunities Fund",
            "Sustainable Future Fund",
            "Emerging Markets Fund",
            "Balanced Advantage Fund",
            "Tech Innovation Fund",
            "Healthcare Leaders Fund",
            "Real Estate Investment Fund",
            "Energy & Infrastructure Fund"
        )

        val adapter =
            ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, funds)
        binding.spinnerFund.setAdapter(adapter)

        binding.spinnerFund.setOnItemClickListener { parent, _, position, _ ->
            val selectedFund = parent.getItemAtPosition(position).toString()

            binding.spinnerFund.setText(selectedFund, false)

            binding.tvFundLabel.text = "Selected: $selectedFund"
        }
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
        }
    }

    private fun handleSubmit() {
        val fundName = binding.spinnerFund.text.toString()
        val investorName = binding.etInvestorName.editText?.text.toString()
        val investmentDate = binding.etInvestmentDate.editText?.text.toString()
        val investmentAmount = binding.etInvestmentAmount.editText?.text.toString()
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
            investmentAmount = investmentAmount.toDouble(),
        )

        if (Constants.UPDATE_JOURNEY) {
            viewModel.updateInvestmentDetail(detail)
        } else {
            viewModel.insertInvestmentDetail(detail)
        }
        Constants.UPDATE_JOURNEY = false
        findNavController().popBackStack()

        clearForm()
    }

    private fun clearForm() {
        binding.spinnerFund.setText("")
        binding.etInvestorName.editText?.text?.clear()
        binding.etInvestmentDate.editText?.text?.clear()
        binding.radioGroupStatus.clearCheck()
        selectedDetail = null
    }

    private fun observeInvestmentPrefilledDetails() {
        viewModel.selectedInvestment.observe(viewLifecycleOwner) { investment ->
            investment?.let {
                selectedDetail = it

                binding.spinnerFund.setText(it.fundName, false)
                binding.etInvestorName.editText?.setText(it.investmentName)
                binding.etInvestmentDate.editText?.setText(it.investmentDate)
                binding.etInvestmentAmount.editText?.setText(it.investmentAmount.toString())

                if (it.investmentStatus == "Active") {
                    binding.radioActive.isChecked = true
                } else if (it.investmentStatus == "Complete") {
                    binding.radioComplete.isChecked = true
                }
            }
        }
    }

}