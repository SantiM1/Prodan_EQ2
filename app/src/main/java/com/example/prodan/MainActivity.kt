package com.example.prodan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.prodan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.selectedItemId = R.id.homeFragment

        val bundle = intent.extras
        val forms = bundle?.getBoolean("forms", false)

        if(forms == true) {
            replaceFragment(FormsFragment())
            binding.bottomNavigationView.selectedItemId = R.id.formsFragment
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.favoritesFragment -> replaceFragment(FavoritesFragment())
                R.id.formsFragment -> replaceFragment(FormsFragment())
                R.id.userFragment -> replaceFragment(UserFragment())
                else -> replaceFragment(ContactFragment())
            }

            true
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}