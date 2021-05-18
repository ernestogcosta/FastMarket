package br.iesb.mobile.fastmarket.ui.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun fazerCadastroELogar(v: View){
        Toast.makeText(context, getString(R.string.toast_cadastro_realizado), Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.acSignUpToMainActivity)
    }

}