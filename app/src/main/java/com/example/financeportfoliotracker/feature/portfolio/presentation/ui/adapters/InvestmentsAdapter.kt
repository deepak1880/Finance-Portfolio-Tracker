package com.example.financeportfoliotracker.feature.portfolio.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.financeportfoliotracker.databinding.InvesterDetailsItemBinding
import com.example.financeportfoliotracker.feature.portfolio.data.model.InvestmentEntity
import com.example.financeportfoliotracker.R

class InvestmentsAdapter(
    private var items: List<InvestmentEntity> = listOf(),
    private val onItemClick: (InvestmentEntity) -> Unit,
    private val onDeleteClick: (InvestmentEntity) -> Unit
) : RecyclerView.Adapter<InvestmentsAdapter.InvestmentViewHolder>() {

    inner class InvestmentViewHolder(
        private val binding: InvesterDetailsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InvestmentEntity) {
            binding.tvFundTitle.text=item.fundName
            binding.tvReturns.text = "₹${item.investmentAmount}"
            binding.tvInvestorName.text = "Investor • ${item.investmentName}"
            binding.tvInvestmentDate.text = "Investment Date : ${item.investmentDate}"
            binding.btnStatus.text = item.investmentStatus
            binding.btnStatus.apply {
                text = item.investmentStatus

                if (item.investmentStatus.equals("Complete", ignoreCase = true)) {
                    backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.success_green)
                } else {
                    backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.dark_blue)
                }
            }
            binding.parentMaterialCardView.setOnClickListener {
                onItemClick(item)
            }
            binding.imgDelete.setOnClickListener {
                onDeleteClick(item)
            }
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

    fun submitList(newItems: List<InvestmentEntity>) {
        items = newItems
        notifyDataSetChanged()
    }
}
