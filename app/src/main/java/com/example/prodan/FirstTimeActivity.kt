package com.example.prodan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodan.databinding.ActivityFirstTimeBinding

class FirstTimeActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = this?.getPreferences(Context.MODE_PRIVATE) ?: return

        binding.buttonExplore.setOnClickListener {
            with(sharedPreferences.edit()) {
                putBoolean("firstTime", false)
                apply()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener {
            with(sharedPreferences.edit()) {
                putBoolean("firstTime", false)
                apply()
            }

            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra("fragmentId", true)
            startActivity(intent)
        }
    }
}