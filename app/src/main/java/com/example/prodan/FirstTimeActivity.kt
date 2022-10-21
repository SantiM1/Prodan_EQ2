package com.example.prodan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.prodan.databinding.ActivityFirstTimeBinding

class FirstTimeActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = this.getPreferences(Context.MODE_PRIVATE) ?: return

        if(!sharedPreferences.getBoolean("firstTime", true)) {
            Log.d("MainActivity", "First Time: " + sharedPreferences.getBoolean("firstTime", true))
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.buttonExplore.setOnClickListener {
            with(sharedPreferences.edit()) {
                putBoolean("firstTime", false)
                apply()
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("forms", false)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener {
            with(sharedPreferences.edit()) {
                putBoolean("firstTime", false)
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("forms", true)
            startActivity(intent)
        }

    }

}