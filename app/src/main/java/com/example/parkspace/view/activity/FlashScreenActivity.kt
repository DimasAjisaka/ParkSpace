package com.example.parkspace.view.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.parkspace.databinding.ActivityFlashScreenBinding
import com.example.parkspace.utils.UserPreverence

class FlashScreenActivity : AppCompatActivity() {
    private var _binding: ActivityFlashScreenBinding? = null
    private val binding get() = _binding!!
    private var onBoardingActivity: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFlashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //check
        val userPreverence = UserPreverence(this)


//        supportActionBar?.hide()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        supportActionBar?.hide()

//        if (Build.VERSION.SDK_INT >= 30){
//            binding.fullscreencontent.windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//        } else {
//            binding.fullscreencontent.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        }

        //animation
        val splashDuration: Long = 2000

        binding.pslogo.alpha = 0f
        binding.pstext.alpha = 0f
        binding.pslogo.translationZ = 800f
        binding.pslogo.translationY = 30f
        binding.pstext.translationZ = -800f
        binding.pstext.translationY = -30f
        binding.pslogo.animate().alpha(1f).translationY(0f).setStartDelay(500).setDuration(500).start()
        binding.pstext.animate().alpha(1f).translationY(0f).setStartDelay(500).setDuration(500).start()

        //next
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this@FlashScreenActivity, OnBoardingActivity::class.java))
//            finish()
//        }, splashDuration.toLong())

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Handler().postDelayed((Runnable {


            // is first time checked
            onBoardingActivity =
                getSharedPreferences("onBoardingActivity", MODE_PRIVATE)
            val isFirstTime: Boolean? = onBoardingActivity?.getBoolean("firstTime", true)
            if (isFirstTime!!) {
                val editor: SharedPreferences.Editor? = onBoardingActivity?.edit()
                editor?.putBoolean("firstTime", false)
                editor?.apply()
                startActivity(Intent(this@FlashScreenActivity, OnBoardingActivity::class.java))
                finish()
            } else {
                if (userPreverence.getLogin()!!){
                    startActivity(Intent(this@FlashScreenActivity, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@FlashScreenActivity, SignActivity::class.java))
                    finish()
                }
            }
        }), splashDuration)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}