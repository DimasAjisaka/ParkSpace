package com.example.parkspace.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.parkspace.adapters.SignHeaderAdapter
import com.example.parkspace.adapters.SignTabPagerAdapter
import com.example.parkspace.databinding.ActivitySignBinding
import com.google.android.material.tabs.TabLayout
import java.util.Objects

class SignActivity : AppCompatActivity() {
    private var _binding: ActivitySignBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       _binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tablay.newTab().let { binding.tablay.addTab(it.setText("Sign In")) }
        binding.tablay.newTab().let { binding.tablay.addTab(it.setText("Sign Up")) }
        binding.tablay.tabGravity = TabLayout.GRAVITY_FILL

        val adapter =
            binding.tablay.tabCount.let { SignTabPagerAdapter(this, supportFragmentManager, it) }
        val adapterHeader =
            binding.tablay.tabCount.let { SignHeaderAdapter(this, supportFragmentManager, it) }
        binding.header.adapter = adapterHeader
        binding.viewpager.adapter = adapter
        binding.header.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablay))
        binding.viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablay))
        binding.tablay.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.header.currentItem = tab?.position ?: 0
                binding.viewpager.currentItem = tab?.position ?: 0
                when (tab?.position){
                    0 -> {
                        binding.header.currentItem = 0
                        binding.viewpager.currentItem = 0
                    }
                    1 -> {
                        binding.header.currentItem = 1
                        binding.viewpager.currentItem = 1
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}