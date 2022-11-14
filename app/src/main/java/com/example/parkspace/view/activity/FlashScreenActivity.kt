package com.example.parkspace.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.parkspace.databinding.ActivityFlashScreenBinding

class FlashScreenActivity : AppCompatActivity() {
    private var _binding: ActivityFlashScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFlashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //animation
        val splashDuration = 2000

        binding.pslogo.alpha = 0f
        binding.pstext.alpha = 0f
        binding.pslogo.translationZ = 800f
        binding.pslogo.translationY = 30f
        binding.pstext.translationZ = -800f
        binding.pstext.translationY = -30f
        binding.pslogo.animate().alpha(1f).translationY(0f).setStartDelay(500).setDuration(500).start()
        binding.pstext.animate().alpha(1f).translationY(0f).setStartDelay(500).setDuration(500).start()

        //next
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@FlashScreenActivity, SignActivity::class.java))
            finish()
        }, splashDuration.toLong())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}