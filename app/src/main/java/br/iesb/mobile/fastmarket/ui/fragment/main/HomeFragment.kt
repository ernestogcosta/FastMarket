package br.iesb.mobile.fastmarket.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun navNewList(v: View){
        findNavController().navigate(R.id.nvHomeNewList)
    }

    fun navLists(v: View){
        findNavController().navigate(R.id.nvHomeLists)
    }

    fun navMarket(v: View){
        findNavController().navigate(R.id.nvHomeMarket)
    }

}