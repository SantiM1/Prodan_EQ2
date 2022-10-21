package com.example.prodan



import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cloudinary.android.MediaManager
import com.example.prodan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    var config: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MediaManager.init(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val preferences = this.getPreferences(Context.MODE_PRIVATE)
        val aceptado = preferences.getBoolean("aviso", false)

        if (!aceptado) {

            var dialog = PrivacyPolicyFragment()

            dialog.show(supportFragmentManager, "customDialog")
            dialog.isCancelable = false


        }
        if(!preferences.getBoolean("Registro", false)) {
            var dialog = RegisterFragment()
            dialog.show(supportFragmentManager, "customDialog")
            dialog.isCancelable = false
        }

        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setupWithNavController(navController)
    }


}
