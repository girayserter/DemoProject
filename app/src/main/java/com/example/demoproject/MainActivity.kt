package com.example.demoproject

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.demoproject.databinding.ActivityMainBinding
import com.example.mymodule.Location


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setUpNavigation()

    }


    /**
     * Setup navigation for fragments
     */
    fun setUpNavigation(){
        var navHostFragment= supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        var navController= navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)

    }
    
}