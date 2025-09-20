package com.example.financeportfoliotracker.core.ui.views

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.financeportfoliotracker.R
import com.example.financeportfoliotracker.core.ui.base.BaseFragment
import com.example.financeportfoliotracker.core.utils.Constants
import com.example.financeportfoliotracker.databinding.FragmentCommonSuccessBinding

class CommonSuccessFragment : BaseFragment<FragmentCommonSuccessBinding>() {

    private lateinit var lottieAnimationView: LottieAnimationView
    private var isUpdateJourney: Boolean = false

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCommonSuccessBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isUpdateJourney = arguments?.getBoolean("isUpdateJourney") ?: false
        Log.e("getFragmentBinding", "onCreate: $isUpdateJourney", )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottieAnimationView = binding.lottieSuccess

        lottieAnimationView.playAnimation()

        binding.root.postDelayed({
            findNavController().popBackStack(R.id.portFolioFragment, false)
        }, 2500)

        binding.tvSuccess.text = if (isUpdateJourney) {
            "Your investment has been successfully updated!"
        } else {
            "Your investment has been successfully added!"
        }
        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }
}