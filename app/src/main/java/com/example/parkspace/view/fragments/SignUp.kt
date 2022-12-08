package com.example.parkspace.view.fragments

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.parkspace.R
import com.example.parkspace.databinding.FragmentSignUpBinding

class SignUp : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)
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

        binding.signin.setOnClickListener {
            val header = activity?.findViewById<ViewPager>(R.id.header)
            val signIn = activity?.findViewById<ViewPager>(R.id.viewpager)

            header!!.currentItem = 0
            signIn!!.currentItem = 0
        }

        binding.push.setOnClickListener {
            val header = activity?.findViewById<ViewPager>(R.id.header)
            val signIn = activity?.findViewById<ViewPager>(R.id.viewpager)

            header!!.currentItem = 0
            signIn!!.currentItem = 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}