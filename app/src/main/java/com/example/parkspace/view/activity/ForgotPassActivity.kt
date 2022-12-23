package com.example.parkspace.view.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.parkspace.R
import com.example.parkspace.databinding.ActivityForgotPassBinding
import com.example.parkspace.view.fragments.ForgotPass

class ForgotPassActivity : AppCompatActivity() {
    private var _binding: ActivityForgotPassBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ForgotPass())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //method replace fragment
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelay,fragment)
        fragmentTransaction.commit()
    }
}