package com.example.prodan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.prodan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.bottomNavigationView

        navView.setupWithNavController(navController)

        //  binding.bottomNavigationView.selectedItemId = R.id.homeFragment

        /*   binding.bottomNavigationView.setOnItemSelectedListener {
               when(it.itemId) {
                   R.id.homeFragment -> replaceFragment(HomeFragment())
                   R.id.favoritesFragment -> replaceFragment(FavoritesFragment())
                   R.id.formsFragment -> replaceFragment(EditProfileFragment())
                   R.id.userFragment -> replaceFragment(UserFragment())
                   else -> replaceFragment(ContactFragment())
               }

               true
           }*/
    }

    /* fun replaceFragment(fragment: Fragment) {
         val fragmentManager = supportFragmentManager
         val fragmentTransaction = fragmentManager.beginTransaction()

         fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
         fragmentTransaction.commit()
     }*/
}