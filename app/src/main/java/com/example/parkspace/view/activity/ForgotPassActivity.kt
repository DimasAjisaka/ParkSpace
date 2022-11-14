package com.example.parkspace.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parkspace.databinding.ActivityForgotPassBinding

class ForgotPassActivity : AppCompatActivity() {
    private var _binding: ActivityForgotPassBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}