package com.example.parkspace.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parkspace.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private var _binding: ActivityProfilBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}