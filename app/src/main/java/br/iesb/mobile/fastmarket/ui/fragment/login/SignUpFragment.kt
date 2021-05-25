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
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    val auth = FirebaseAuth.getInstance()

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
        if(binding.etEmailSignup.text.toString().isEmpty() ||
            binding.etConfirmarEmailSignUp.text.toString().isEmpty() ||
            binding.etPassword.text.toString().isEmpty() ||
            binding.etConfirmarPassword.text.toString().isEmpty()){
                Toast.makeText(context, getString(R.string.signup_et_ausente), Toast.LENGTH_LONG).show()
        }else if(!binding.etEmailSignup.text.toString().equals(binding.etConfirmarEmailSignUp.text.toString())){
            Toast.makeText(context, getString(R.string.signup_emails_diferentes), Toast.LENGTH_LONG).show()
        }else if(!binding.etPassword.text.toString().equals(binding.etConfirmarPassword.text.toString())){
            Toast.makeText(context, getString(R.string.signup_passwords_diferentes), Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(binding.etEmailSignup.text.toString(), binding.etPassword.text.toString())

            Toast.makeText(context, getString(R.string.signup_cadastro_realizado), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.acSignUpToMainActivity)
        }

    }

}