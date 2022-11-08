package com.example.parkspace.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parkspace.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private var _binding: ActivityAboutBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}