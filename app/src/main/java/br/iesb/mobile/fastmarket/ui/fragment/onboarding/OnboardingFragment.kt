package br.iesb.mobile.fastmarket.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.LayoutonboardingBinding
import br.iesb.mobile.fastmarket.ui.adapter.OnboardingAdapter
import br.iesb.mobile.fastmarket.ui.fragment.onboarding.OnboardingPage1
import br.iesb.mobile.fastmarket.ui.fragment.onboarding.OnboardingPage2
import br.iesb.mobile.fastmarket.ui.fragment.onboarding.OnboardingFragmentPage3

class OnboardingFragment : Fragment() {
    private lateinit var binding: LayoutonboardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = LayoutonboardingBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        val pages = arrayListOf(
            OnboardingPage1(),
            OnboardingPage2(),
            OnboardingFragmentPage3()
        )
        binding.viewpageOnboarding.adapter = OnboardingAdapter(
            pages,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.wormDotsIndicator.setViewPager2(binding.viewpageOnboarding)
        return binding.root
    }
    @SuppressWarnings
    fun start(v: View) {

    }

    fun irOnboardingLogin(v: View){
        findNavController().navigate(R.id.acOnboardingToLogin)
    }
}