package com.example.parkspace.view.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkspace.databinding.FragmentCreateNewPassBinding
import com.example.parkspace.view.activity.SignActivity

class CreateNewPass : Fragment() {
    private var _binding: FragmentCreateNewPassBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNewPassBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //toggle
        binding.invisible.setOnClickListener{
            binding.invisible.visibility = View.INVISIBLE
            binding.visible.visibility = View.VISIBLE
            binding.inputnewpass.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }

        binding.visible.setOnClickListener{
            binding.invisible.visibility = View.VISIBLE
            binding.visible.visibility = View.INVISIBLE
            binding.inputnewpass.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        binding.invisible2.setOnClickListener{
            binding.invisible2.visibility = View.INVISIBLE
            binding.visible2.visibility = View.VISIBLE
            binding.inputconpass.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }

        binding.visible2.setOnClickListener{
            binding.invisible2.visibility = View.VISIBLE
            binding.visible2.visibility = View.INVISIBLE
            binding.inputconpass.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        binding.push.setOnClickListener {
            startActivity(Intent(context,SignActivity::class.java))
            activity?.finish()
        }
        binding.backarrow.setOnClickListener { activity?.finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}