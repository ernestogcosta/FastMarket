package br.iesb.mobile.fastmarket.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.ui.fragment.onboarding.OnboardingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavView)
        var manager = supportFragmentManager
        var navHost = manager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment
        var navController = navHost.navController
        bottomNavigationView.setupWithNavController(navController)
//        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        return true
//    }

}