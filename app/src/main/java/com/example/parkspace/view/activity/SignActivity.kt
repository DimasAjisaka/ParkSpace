package com.example.parkspace.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.parkspace.adapters.SignTabPagerAdapter
import com.example.parkspace.databinding.ActivitySignBinding
import com.google.android.material.tabs.TabLayout
import java.util.Objects

class SignActivity : AppCompatActivity() {
    private lateinit var tabLay: TabLayout
    private lateinit var viewPage: ViewPager
    private var _binding: ActivitySignBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       _binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.term?.text = "Term"
        binding.policy?.text = "Policy"

//        binding.tablay?.newTab()?.let { binding.tablay?.addTab(it.setText("Sign In")) }
//        binding.tablay?.newTab()?.let { binding.tablay?.addTab(it.setText("Sign Up")) }
//        binding.tablay?.tabGravity = TabLayout.GRAVITY_FILL
//
//        val adapter =
//            binding.tablay?.tabCount?.let { SignTabPagerAdapter(this, supportFragmentManager, it) }
//        binding.viewpager?.adapter = adapter
//        binding.tablay?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if (tab != null) {
//                    binding.viewpager?.currentItem = tab.position
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}