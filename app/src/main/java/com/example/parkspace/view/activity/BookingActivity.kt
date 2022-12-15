package com.example.parkspace.view.activity

import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
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

        //bar
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val view = getWindow().decorView

        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                window.statusBarColor = Color.parseColor("#003346")
                view.systemUiVisibility = view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                window.statusBarColor = Color.parseColor("#AAE5FF")
                view.systemUiVisibility = view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

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