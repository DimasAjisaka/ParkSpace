package com.example.parkspace.view.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.parkspace.R
import com.example.parkspace.databinding.FragmentFirstFloorBinding
import com.example.parkspace.databinding.TicketBottomSheetBinding
import com.example.parkspace.models.data.TicketModel
import com.example.parkspace.models.responses.FloorItem
import com.example.parkspace.utils.LoadingDialog
import com.example.parkspace.utils.Resource
import com.example.parkspace.utils.UserPreverence
import com.example.parkspace.viewmodels.FloorViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response

class FirstFloor : Fragment() {

    private val floorViewModel: FloorViewModel by viewModels()
    private var loadingDialog: LoadingDialog? = null

    private var _binding: FragmentFirstFloorBinding? = null
    private val binding get() = _binding!!

    private var slot = 0



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPreverence = UserPreverence(requireActivity())
        loadingDialog = LoadingDialog(requireActivity())
        //bottom sheet
        val dialog = context?.let { BottomSheetDialog(it) }
        val dialogBinding = TicketBottomSheetBinding.inflate(layoutInflater)
        dialog?.setContentView(dialogBinding.root)
        binding.button.setOnClickListener {
            floorViewModel.ticket(TicketModel("A", slot.toString()),userPreverence.getToken()!!).observe(viewLifecycleOwner){
                if (it != null){
                    when(it){
                        is Resource.Loading -> loadingDialog?.showDialog()
                        is Resource.Success -> {
                            loadingDialog?.hideDialog()
                            val response = it.data[0]
                            dialogBinding.no.text = "A - $slot"
                            dialogBinding.bislot.text = response.parkingCode
                            dialogBinding.nameslot.text = response.name
                            dialog?.show()
                        }
                        is Resource.Error -> {
                            loadingDialog?.hideDialog()
                            Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()

                        }
                    }
                }
            }
        }
        dialogBinding.button.setOnClickListener { activity?.finish() }
        dialog?.setCancelable(false)
//        dialogBinding.close.setOnClickListener { dialog?.dismiss() }

        setUpFloor(userPreverence.getToken()!!)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstFloorBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setUpFloor(token: String){
        floorViewModel.parkingSlots(token, "a").observe(viewLifecycleOwner){
            if (it != null){
                when(it) {
                    is Resource.Loading -> loadingDialog?.showDialog()
                    is Resource.Success -> {
                        loadingDialog?.hideDialog()
                        val response = it.data
                        setChoosen(response)
                        binding.a1.setBackgroundColor(
                            when (response[0].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a2.setBackgroundColor(
                            when (response[1].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a3.setBackgroundColor(
                            when (response[2].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a4.setBackgroundColor(
                            when (response[3].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a5.setBackgroundColor(
                            when (response[4].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a6.setBackgroundColor(
                            when (response[5].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a7.setBackgroundColor(
                            when (response[6].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a8.setBackgroundColor(
                            when (response[7].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a9.setBackgroundColor(
                            when (response[8].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a10.setBackgroundColor(
                            when (response[9].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a11.setBackgroundColor(
                            when (response[10].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a12.setBackgroundColor(
                            when (response[11].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a13.setBackgroundColor(
                            when (response[12].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a14.setBackgroundColor(
                            when (response[13].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a15.setBackgroundColor(
                            when (response[14].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a16.setBackgroundColor(
                            when (response[15].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a17.setBackgroundColor(
                            when (response[16].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a18.setBackgroundColor(
                            when (response[17].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a19.setBackgroundColor(
                            when (response[18].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a20.setBackgroundColor(
                            when (response[19].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a21.setBackgroundColor(
                            when (response[20].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a22.setBackgroundColor(
                            when (response[21].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a23.setBackgroundColor(
                            when (response[22].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a24.setBackgroundColor(
                            when (response[23].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a25.setBackgroundColor(
                            when (response[24].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a26.setBackgroundColor(
                            when (response[25].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a27.setBackgroundColor(
                            when (response[26].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a28.setBackgroundColor(
                            when (response[27].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a29.setBackgroundColor(
                            when (response[28].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a30.setBackgroundColor(
                            when (response[29].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a31.setBackgroundColor(
                            when (response[30].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a32.setBackgroundColor(
                            when (response[31].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a33.setBackgroundColor(
                            when (response[32].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a34.setBackgroundColor(
                            when (response[33].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a35.setBackgroundColor(
                            when (response[34].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a36.setBackgroundColor(
                            when (response[35].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a37.setBackgroundColor(
                            when (response[36].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a38.setBackgroundColor(
                            when (response[37].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a39.setBackgroundColor(
                            when (response[38].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a40.setBackgroundColor(
                            when (response[39].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a41.setBackgroundColor(
                            when (response[40].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a42.setBackgroundColor(
                            when (response[41].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a43.setBackgroundColor(
                            when (response[42].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                        binding.a44.setBackgroundColor(
                            when (response[43].status){
                                "Available" -> ContextCompat.getColor(requireActivity(),R.color.bluish_cyan)
                                "Reserved" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                "Filled" -> ContextCompat.getColor(requireActivity(), R.color.shuttle_grey)
                                else -> { ContextCompat.getColor(requireActivity(), R.color.bluish_cyan) }
                            }
                        )
                    }
                    is Resource.Error -> {
                        loadingDialog?.hideDialog()
                        Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setChoosen(data: List<FloorItem>){
        binding.a1.setOnClickListener {
            if (!data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!!){
                binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
                binding.button.visibility = View.VISIBLE
                slot = 1
            }
            when{
                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
            }
        }
        binding.a2.setOnClickListener {
            if (!data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!!){
                binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
                binding.button.visibility = View.VISIBLE
                slot = 2
            }
            when{
                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
            }
        }
//        binding.a3.setOnClickListener {
//            if (!data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!!){
//                binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a4.setOnClickListener {
//            if (!data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!!){
//                binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a5.setOnClickListener {
//            if (!data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!!){
//                binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a6.setOnClickListener {
//            if (!data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!!){
//                binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a7.setOnClickListener {
//            if (!data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!!){
//                binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a8.setOnClickListener {
//            if (!data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!!){
//                binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a9.setOnClickListener {
//            if (!data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!!){
//                binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a10.setOnClickListener {
//            if (!data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!!){
//                binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a11.setOnClickListener {
//            if (!data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!!){
//                binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a12.setOnClickListener {
//            if (!data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!!){
//                binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a13.setOnClickListener {
//            if (!data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!!){
//                binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a14.setOnClickListener {
//            if (!data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!!){
//                binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a15.setOnClickListener {
//            if (!data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!!){
//                binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a16.setOnClickListener {
//            if (!data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!!){
//                binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a17.setOnClickListener {
//            if (!data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!!){
//                binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a18.setOnClickListener {
//            if (!data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!!){
//                binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a19.setOnClickListener {
//            if (!data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!!){
//                binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a20.setOnClickListener {
//            if (!data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!!){
//                binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a21.setOnClickListener {
//            if (!data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!!){
//                binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a22.setOnClickListener {
//            if (!data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!!){
//                binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a23.setOnClickListener {
//            if (!data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!!){
//                binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a24.setOnClickListener {
//            if (!data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!!){
//                binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a25.setOnClickListener {
//            if (!data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!!){
//                binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a26.setOnClickListener {
//            if (!data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!!){
//                binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a27.setOnClickListener {
//            if (!data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!!){
//                binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a28.setOnClickListener {
//            if (!data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!!){
//                binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a29.setOnClickListener {
//            if (!data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!!){
//                binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a30.setOnClickListener {
//            if (!data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!!){
//                binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a31.setOnClickListener {
//            if (!data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!!){
//                binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a32.setOnClickListener {
//            if (!data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!!){
//                binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a33.setOnClickListener {
//            if (!data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!!){
//                binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a34.setOnClickListener {
//            if (!data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!!){
//                binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a35.setOnClickListener {
//            if (!data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!!){
//                binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a36.setOnClickListener {
//            if (!data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!!){
//                binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a37.setOnClickListener {
//            if (!data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!!){
//                binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a38.setOnClickListener {
//            if (!data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!!){
//                binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a39.setOnClickListener {
//            if (!data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!!){
//                binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a40.setOnClickListener {
//            if (!data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!!){
//                binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a41.setOnClickListener {
//            if (!data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!!){
//                binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a42.setOnClickListener {
//            if (!data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!!){
//                binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a43.setOnClickListener {
//            if (!data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!!){
//                binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!! -> binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }
//        binding.a44.setOnClickListener {
//            if (!data[43].status?.equals("Reserved")!!&&!data[43].status?.equals("Filled")!!){
//                binding.a44.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.hawaiian_passion))
//            }
//            when{
//                !data[1].status?.equals("Reserved")!!&&!data[1].status?.equals("Filled")!! -> binding.a2.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[2].status?.equals("Reserved")!!&&!data[2].status?.equals("Filled")!! -> binding.a3.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[3].status?.equals("Reserved")!!&&!data[3].status?.equals("Filled")!! -> binding.a4.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[4].status?.equals("Reserved")!!&&!data[4].status?.equals("Filled")!! -> binding.a5.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[5].status?.equals("Reserved")!!&&!data[5].status?.equals("Filled")!! -> binding.a6.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[6].status?.equals("Reserved")!!&&!data[6].status?.equals("Filled")!! -> binding.a7.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[7].status?.equals("Reserved")!!&&!data[7].status?.equals("Filled")!! -> binding.a8.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[8].status?.equals("Reserved")!!&&!data[8].status?.equals("Filled")!! -> binding.a9.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[9].status?.equals("Reserved")!!&&!data[9].status?.equals("Filled")!! -> binding.a10.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[10].status?.equals("Reserved")!!&&!data[10].status?.equals("Filled")!! -> binding.a11.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[11].status?.equals("Reserved")!!&&!data[11].status?.equals("Filled")!! -> binding.a12.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[12].status?.equals("Reserved")!!&&!data[12].status?.equals("Filled")!! -> binding.a13.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[13].status?.equals("Reserved")!!&&!data[13].status?.equals("Filled")!! -> binding.a14.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[14].status?.equals("Reserved")!!&&!data[14].status?.equals("Filled")!! -> binding.a15.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[15].status?.equals("Reserved")!!&&!data[15].status?.equals("Filled")!! -> binding.a16.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[16].status?.equals("Reserved")!!&&!data[16].status?.equals("Filled")!! -> binding.a17.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[17].status?.equals("Reserved")!!&&!data[17].status?.equals("Filled")!! -> binding.a18.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[18].status?.equals("Reserved")!!&&!data[18].status?.equals("Filled")!! -> binding.a19.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[19].status?.equals("Reserved")!!&&!data[19].status?.equals("Filled")!! -> binding.a20.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[20].status?.equals("Reserved")!!&&!data[20].status?.equals("Filled")!! -> binding.a21.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[21].status?.equals("Reserved")!!&&!data[21].status?.equals("Filled")!! -> binding.a22.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[22].status?.equals("Reserved")!!&&!data[22].status?.equals("Filled")!! -> binding.a23.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[23].status?.equals("Reserved")!!&&!data[23].status?.equals("Filled")!! -> binding.a24.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[24].status?.equals("Reserved")!!&&!data[24].status?.equals("Filled")!! -> binding.a25.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[25].status?.equals("Reserved")!!&&!data[25].status?.equals("Filled")!! -> binding.a26.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[26].status?.equals("Reserved")!!&&!data[26].status?.equals("Filled")!! -> binding.a27.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[27].status?.equals("Reserved")!!&&!data[27].status?.equals("Filled")!! -> binding.a28.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[28].status?.equals("Reserved")!!&&!data[28].status?.equals("Filled")!! -> binding.a29.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[29].status?.equals("Reserved")!!&&!data[29].status?.equals("Filled")!! -> binding.a30.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[30].status?.equals("Reserved")!!&&!data[30].status?.equals("Filled")!! -> binding.a31.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[31].status?.equals("Reserved")!!&&!data[31].status?.equals("Filled")!! -> binding.a32.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[32].status?.equals("Reserved")!!&&!data[32].status?.equals("Filled")!! -> binding.a33.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[33].status?.equals("Reserved")!!&&!data[33].status?.equals("Filled")!! -> binding.a34.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[34].status?.equals("Reserved")!!&&!data[34].status?.equals("Filled")!! -> binding.a35.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[35].status?.equals("Reserved")!!&&!data[35].status?.equals("Filled")!! -> binding.a36.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[36].status?.equals("Reserved")!!&&!data[36].status?.equals("Filled")!! -> binding.a37.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[37].status?.equals("Reserved")!!&&!data[37].status?.equals("Filled")!! -> binding.a38.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[38].status?.equals("Reserved")!!&&!data[38].status?.equals("Filled")!! -> binding.a39.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[39].status?.equals("Reserved")!!&&!data[39].status?.equals("Filled")!! -> binding.a40.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[40].status?.equals("Reserved")!!&&!data[40].status?.equals("Filled")!! -> binding.a41.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[41].status?.equals("Reserved")!!&&!data[41].status?.equals("Filled")!! -> binding.a42.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[42].status?.equals("Reserved")!!&&!data[42].status?.equals("Filled")!! -> binding.a43.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//                !data[0].status?.equals("Reserved")!!&&!data[0].status?.equals("Filled")!! -> binding.a1.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.bluish_cyan))
//            }
//        }

    }

//    //request
//    private fun reqTicket(){
//        binding.button.setOnClickListener { Toast.makeText(context,) }
//    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}