package com.example.parkspace.view.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.parkspace.R
import com.example.parkspace.databinding.FragmentSignInBinding
import com.example.parkspace.view.activity.ForgotPassActivity

class SignIn : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    //tooggle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.invisible.setOnClickListener{
            binding.invisible.visibility = View.INVISIBLE
            binding.visible.visibility = View.VISIBLE
            binding.inputpass.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }

        binding.visible.setOnClickListener{
            binding.invisible.visibility = View.VISIBLE
            binding.visible.visibility = View.INVISIBLE
            binding.inputpass.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        binding.signup.setOnClickListener {
            val header = activity?.findViewById<ViewPager>(R.id.header)
            val signUp = activity?.findViewById<ViewPager>(R.id.viewpager)

            header!!.currentItem = 1
            signUp!!.currentItem = 1
        }

        binding.forgotpass.setOnClickListener {
            startActivity(Intent(context,ForgotPassActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}