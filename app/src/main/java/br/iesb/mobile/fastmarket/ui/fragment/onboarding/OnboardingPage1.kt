package br.iesb.mobile.fastmarket.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.Layoutonboardingpg1Binding
import br.iesb.mobile.fastmarket.databinding.Layoutonboardingpg3Binding


class OnboardingPage1 : Fragment() {
    private lateinit var binding: Layoutonboardingpg1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Layoutonboardingpg1Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }
}