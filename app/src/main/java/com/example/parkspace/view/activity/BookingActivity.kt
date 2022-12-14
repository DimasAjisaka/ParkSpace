package com.example.parkspace.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.parkspace.R
import com.example.parkspace.adapters.FloorAdapter
import com.example.parkspace.databinding.ActivityBookingBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BookingActivity : AppCompatActivity() {
    private var _binding: ActivityBookingBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backarrow.setOnClickListener { finish() }

        //tablay
        val tabLayout = findViewById<TabLayout>(R.id.tablay)
        //viepager
        val viewPager2 = findViewById<ViewPager2>(R.id.viewpager2)
        //adapter
        val adapter = FloorAdapter(supportFragmentManager,lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager2){tab, position->
            when(position){
                0->{ tab.text = "1st" }
                1->{ tab.text = "2nd" }
                2->{ tab.text = "3rd" }
                3->{ tab.text = "4th" }
                4->{ tab.text = "5th" }
                5->{ tab.text = "6th" }
                6->{ tab.text = "7th" }
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}