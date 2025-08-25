package com.example.financeportfoliotracker.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VBinding : ViewBinding> : Fragment() {


    private var _binding: VBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        return binding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialSetUp()
        apiCall()
        setUpObserver()
        setUpOnClickListeners()
    }

    open fun initialSetUp() {

    }

    open fun apiCall() {

    }

    open fun setUpObserver() {

    }

    open fun setUpOnClickListeners() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}