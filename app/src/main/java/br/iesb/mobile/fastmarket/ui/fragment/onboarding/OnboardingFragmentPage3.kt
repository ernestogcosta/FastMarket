package br.iesb.mobile.fastmarket.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.Layoutonboardingpg3Binding

class OnboardingFragmentPage3 : Fragment() {
    private lateinit var binding: Layoutonboardingpg3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Layoutonboardingpg3Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }
}