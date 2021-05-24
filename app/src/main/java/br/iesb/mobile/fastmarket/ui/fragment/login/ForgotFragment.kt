package br.iesb.mobile.fastmarket.ui.fragment.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentForgotBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotFragment : Fragment() {

    private lateinit var binding: FragmentForgotBinding
    val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun enviarEmail(v: View){
        if(binding.etEmailForgot.text.toString().isEmpty()){
            Toast.makeText(context, getString(R.string.forgot_email_missing), Toast.LENGTH_LONG).show()
        }else{
            auth.sendPasswordResetEmail(binding.etEmailForgot.text.toString()).addOnCompleteListener { it ->
                if(it.isSuccessful){
                    Toast.makeText(context, getString(R.string.reset_password_email_sent), Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.acForgotToLogin)
                }else{
                    Toast.makeText(context, getString(R.string.forgot_erro), Toast.LENGTH_LONG).show()
                }
            }
        }


    }

}