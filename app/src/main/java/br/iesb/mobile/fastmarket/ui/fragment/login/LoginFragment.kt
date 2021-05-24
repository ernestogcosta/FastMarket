package br.iesb.mobile.fastmarket.ui.fragment.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun irSignUp(v: View){
        findNavController().navigate(R.id.acLoginToSignUp)
    }

    fun fazerLogin(v: View){
        if(binding.etEmailLogin.text.toString().isEmpty()){
            Toast.makeText(context, getString(R.string.login_email_ausente), Toast.LENGTH_LONG).show()
        }else if(binding.etPasswordLogin.text.toString().isEmpty()){
            Toast.makeText(context, getString(R.string.login_password_ausente), Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(binding.etEmailLogin.text.toString(), binding.etPasswordLogin.text.toString()).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(context, getString(R.string.login_success), Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.acLoginToMainActivity)
                }else{
                    Toast.makeText(context, getString(R.string.login_email_senha_errado), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun irForgot(v: View){
        findNavController().navigate(R.id.acLoginToForgot)
    }


}