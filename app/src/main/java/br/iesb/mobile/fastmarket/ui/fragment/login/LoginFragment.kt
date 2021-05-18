package br.iesb.mobile.fastmarket.ui.fragment.login

import android.os.Bundle
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
//        auth.signInWithEmailAndPassword(binding.etEmailLogin.text.toString(), binding.etPasswordLogin.text.toString()).addOnCompleteListener{
//            if(it.isSuccessful){
//                Toast.makeText(context, "foi", Toast.LENGTH_LONG).show()
//                findNavController().navigate(R.id.acLoginToMainActivity)
//            }else{
//                Toast.makeText(context, binding.etPasswordLogin.text.toString(), Toast.LENGTH_LONG).show()
//            }
//        }

        findNavController().navigate(R.id.acLoginToMainActivity)

    }

    fun irForgot(v: View){
        findNavController().navigate(R.id.acLoginToForgot)
    }


}