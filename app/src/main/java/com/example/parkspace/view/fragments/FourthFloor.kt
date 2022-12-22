package com.example.parkspace.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkspace.R
import com.example.parkspace.databinding.FragmentFourthFloorBinding
import com.example.parkspace.databinding.TicketBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class FourthFloor : Fragment() {
    private var _binding: FragmentFourthFloorBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bottom sheet
        val dialog = context?.let { BottomSheetDialog(it) }
        val dialogBinding = TicketBottomSheetBinding.inflate(layoutInflater)
        dialog?.setContentView(dialogBinding.root)
        binding.button.setOnClickListener { dialog?.show() }
        dialogBinding.button.setOnClickListener { activity?.finish() }
        dialog?.setCancelable(false)
//        dialogBinding.close.setOnClickListener { dialog?.dismiss() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFourthFloorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}