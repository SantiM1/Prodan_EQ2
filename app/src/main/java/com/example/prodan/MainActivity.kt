package com.example.prodan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.prodan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.selectedItemId = R.id.homeFragment

        val sharedPreferences = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        if(sharedPreferences.getBoolean("firstTime", true)) {
            val intent = Intent(this, FirstTimeActivity::class.java)
            startActivity(intent)
        }
        /*else {
            val bundle: Bundle? = intent.extras
            val register = bundle!!.getBoolean("fragmentId", false)

            binding.bottomNavigationView.selectedItemId = R.id.formsFragment
            replaceFragment(FormsFragment())
        }*/

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