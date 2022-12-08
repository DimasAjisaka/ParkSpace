package com.example.parkspace.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.parkspace.R
import com.example.parkspace.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private var _binding: ActivityProfilBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(R.drawable.avatar).circleCrop().into(binding.image)
        binding.backarrow.setOnClickListener { finish() }
        binding.about.setOnClickListener { startActivity(Intent(this, AboutActivity::class.java)) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}