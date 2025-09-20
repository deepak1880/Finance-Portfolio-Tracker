package com.example.financeportfoliotracker.feature.portfolio.presentation.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeportfoliotracker.core.ui.base.BaseFragment
import com.example.financeportfoliotracker.core.utils.Constants
import com.example.financeportfoliotracker.databinding.FragmentPortFolioBinding
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import com.example.financeportfoliotracker.feature.portfolio.presentation.ui.adapters.InvestmentsAdapter
import com.example.financeportfoliotracker.feature.portfolio.presentation.viewmodel.PortFolioViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortFolioFragment : BaseFragment<FragmentPortFolioBinding>() {

    private val viewModel: PortFolioViewModel by viewModels()
    private lateinit var adapter: InvestmentsAdapter
    private var allInvestments: List<InvestmentEntity> = emptyList()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPortFolioBinding.inflate(inflater, container, false)


    override fun initialSetUp() {
        binding.tabLayout.apply {
            addTab(newTab().setText("All"))
            addTab(newTab().setText("Active"))
            addTab(newTab().setText("Completed"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    filterList(tab?.text.toString())
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        adapter = InvestmentsAdapter(
            onItemClick = { investment ->
                Constants.UPDATE_JOURNEY = true
                val action =
                    PortFolioFragmentDirections.actionPortFolioFragmentToInvestmentDetailsFragment()
                val bundle = Bundle().apply {
                    putInt("investmentId", investment.investmentId)
                }
                findNavController().navigate(action.actionId, bundle)
            },
            onDeleteClick = { investment ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete Investment")
                    .setMessage("Are you sure you want to delete this investment?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.deleteInvestment(investment)
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        )

        binding.rvInvestorDetails.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@PortFolioFragment.adapter
        }

        binding.rvInvestorDetails.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@PortFolioFragment.adapter
        }

        viewModel.investments.observe(viewLifecycleOwner) { investments ->
            allInvestments = investments
            if (investments.isNotEmpty()) {
                binding.rvInvestorDetails.visibility = View.VISIBLE
                binding.noDataFound.visibility = View.GONE
                binding.tvNoDataFound.visibility = View.GONE
                allInvestments = investments
                val currentTab = binding.tabLayout.getTabAt(binding.tabLayout.selectedTabPosition)?.text.toString()
                filterList(currentTab)
            } else {
                binding.rvInvestorDetails.visibility = View.GONE
                binding.noDataFound.visibility = View.VISIBLE
                binding.tvNoDataFound.visibility = View.VISIBLE
            }
        }

        viewModel.fetchInvestments()
    }

    private fun filterList(filter: String) {
        val filtered = when (filter) {
            "Active" -> allInvestments.filter { it.investmentStatus.equals("Active", true) }
            "Completed" -> allInvestments.filter { it.investmentStatus.equals("Complete", true) }
            else -> allInvestments
        }
        if (filtered.isEmpty()) {
            binding.rvInvestorDetails.visibility = View.GONE
            binding.noDataFound.visibility = View.VISIBLE
            binding.tvNoDataFound.visibility = View.VISIBLE
        } else {
            binding.rvInvestorDetails.visibility = View.VISIBLE
            binding.noDataFound.visibility = View.GONE
            binding.tvNoDataFound.visibility = View.GONE
        }
        adapter.submitList(filtered)
    }

    override fun setUpOnClickListeners() {
        super.setUpOnClickListeners()
        binding.fabAddInvestment.setOnClickListener {
            val action = PortFolioFragmentDirections.actionPortFolioFragmentToInvestmentDetailsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchInvestments()
    }
}