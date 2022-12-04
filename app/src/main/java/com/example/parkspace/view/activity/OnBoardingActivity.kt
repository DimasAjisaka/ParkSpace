package com.example.parkspace.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.parkspace.R
import com.example.parkspace.adapters.OnBoardingSliderAdapter
import com.example.parkspace.databinding.ActivityOnBoardingBinding
import com.example.parkspace.view.onboarding.OnBoarding

class OnBoardingActivity : AppCompatActivity() {
    private var _binding: ActivityOnBoardingBinding? = null
    private val binding get() = _binding!!
    private val onBoardingSliderAdapter = OnBoardingSliderAdapter(
        listOf(
            OnBoarding(
                "Find Your Parking Space",
            "SEARCH and FIND to GET KNOW",
                R.drawable.find_park_icon
            ),
            OnBoarding(
                "Book Your Parking Space",
                "BOOK your parking area",
                R.drawable.booking_icon
            ),
            OnBoarding(
                "Scan Parking Ticket",
                "SEARCH and FIND available parking area",
                R.drawable.scan_icon
            ),
            OnBoarding(
                "Start To Find Your Parking Space",
                "",
                R.drawable.pslogo
            )
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sliderView.adapter = onBoardingSliderAdapter
        setupIndicators()
        setCurrentIndicators(0)
        binding.sliderView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })
        binding.buttonNext.setOnClickListener{
            if (binding.sliderView.currentItem + 1 < onBoardingSliderAdapter.itemCount){
                binding.sliderView.currentItem += 1
            }else{
                Intent(applicationContext, SignActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(onBoardingSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicators(index: Int){
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = binding.indicatorContainer.get(i) as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}