package com.example.financeportfoliotracker.feature.updates.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeportfoliotracker.core.ui.base.BaseFragment
import com.example.financeportfoliotracker.databinding.FragmentNewDetailBinding
import com.example.financeportfoliotracker.feature.updates.presentation.ui.adapter.NewsAdapter
import com.example.financeportfoliotracker.feature.updates.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewDetailFragment : BaseFragment<FragmentNewDetailBinding>() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewDetailBinding.inflate(inflater, container, false)

    override fun initialSetUp() {
        super.initialSetUp()
        newsAdapter = NewsAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        setupRecyclerView()

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            newsAdapter = NewsAdapter(posts)
            binding.recyclerView.adapter = newsAdapter
        }
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            newsAdapter.updateData(posts)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setUpOnClickListeners() {
        super.setUpOnClickListeners()
    }
}