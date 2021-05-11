package br.iesb.mobile.fastmarket.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.ui.fragment.onboarding.OnboardingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var onboard: OnboardingFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onboard = OnboardingFragment()
        setFragment(onboard)
    }

    private fun setFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_fragments, fragment)
        fragmentTransaction.commit()
    }
    /*
    private fun setFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_fragments, fragment)
        fragmentTransaction.commit()
    }
    onboard = OnboardingFragment()
    setFragment(onboard)

    ISSO ACIMA É UMA FUNÇÃO PARA TESTAR ALGUM FRAGMENT ESPECIFICADO NO CÓDIGO.
    FICA FALTANDO TER O FRAMELAYOUT PARA RECEBER O FRAGMENT NA HORA DE RODAR O APP.
    */
}