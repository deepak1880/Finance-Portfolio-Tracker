package com.example.financeportfoliotracker.feature.portfolio.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financeportfoliotracker.databinding.InvesterDetailsItemBinding
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity

class InvestmentsAdapter(
    private var items: List<InvestmentEntity> = listOf()
) : RecyclerView.Adapter<InvestmentsAdapter.InvestmentViewHolder>() {

    inner class InvestmentViewHolder(
        private val binding: InvesterDetailsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InvestmentEntity) {
            binding.tvReturns.text = "₹${item.investmentAmount}"
            binding.tvInvestorName.text = "Investor • ${item.investmentName}"
            binding.tvInvestmentDate.text = "Investment Date : ${item.investmentDate}"
            binding.btnStatus.text = item.investmentStatus
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestmentViewHolder {
        val binding = InvesterDetailsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InvestmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InvestmentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setData(newItems: List<InvestmentEntity>) {
        items = newItems
        notifyDataSetChanged()
    }
}
