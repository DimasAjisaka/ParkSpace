package com.example.parkspace.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.parkspace.R
import com.example.parkspace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.profiles.setOnClickListener { startActivity(Intent(this, ProfilActivity::class.java)) }
        binding.button.setOnClickListener { startActivity(Intent(this, BookingActivity::class.java)) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}