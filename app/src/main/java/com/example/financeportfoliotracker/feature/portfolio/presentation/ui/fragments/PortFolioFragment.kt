package com.example.financeportfoliotracker.feature.portfolio.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.financeportfoliotracker.core.ui.base.BaseFragment
import com.example.financeportfoliotracker.databinding.FragmentPortFolioBinding
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import com.example.financeportfoliotracker.feature.portfolio.presentation.ui.adapters.InvestmentsAdapter
import com.example.financeportfoliotracker.feature.portfolio.presentation.viewmodel.PortFolioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortFolioFragment : BaseFragment<FragmentPortFolioBinding>() {

    private var investmentList = listOf<InvestmentEntity>()
    private val viewModel: PortFolioViewModel by viewModels()
    private lateinit var adapter: InvestmentsAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPortFolioBinding.inflate(inflater, container, false)

    override fun initialSetUp() {

        binding.tabLayout.apply {
            addTab(newTab().setText("All"))
            addTab(newTab().setText("Active"))
            addTab(newTab().setText("Completed"))
        }
        adapter = InvestmentsAdapter()
        binding.rvInvestorDetails.adapter = adapter

        viewModel.investments.observe(viewLifecycleOwner) { investments ->
            if (investments.isNotEmpty()) {
                binding.rvInvestorDetails.visibility = View.VISIBLE
                adapter.setData(investments)
            } else {
                binding.rvInvestorDetails.visibility = View.GONE
            }
        }

        viewModel.fetchInvestments()

    }

    override fun setUpOnClickListeners() {
        super.setUpOnClickListeners()

//        binding.fabAddInvestment.setOnClickListener {
//            findNavController().navigate(R.id.action_portFolioFragment_to_investmentDetailsFragment)
//        }
    }
}