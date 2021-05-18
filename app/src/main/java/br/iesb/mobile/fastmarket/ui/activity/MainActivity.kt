package br.iesb.mobile.fastmarket.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.ui.fragment.onboarding.OnboardingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}