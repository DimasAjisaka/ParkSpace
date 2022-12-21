package com.example.parkspace.view.fragments

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.parkspace.R
import com.example.parkspace.databinding.FragmentSignUpBinding
import com.example.parkspace.models.data.RegisterModel
import com.example.parkspace.utils.LoadingDialog
import com.example.parkspace.utils.Resource
import com.example.parkspace.viewmodels.AuthViewModel

class SignUp : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    //register
    private val authViewModel: AuthViewModel by viewModels()

    //loading
    private var loadingDialog: LoadingDialog? = null

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
//        register
        loadingDialog = LoadingDialog(requireActivity())
        register()

        // viewTable
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

//        binding.push.setOnClickListener {
//            val header = activity?.findViewById<ViewPager>(R.id.header)
//            val signIn = activity?.findViewById<ViewPager>(R.id.viewpager)
//
//            header!!.currentItem = 0
//            signIn!!.currentItem = 0
//        }
    }

    //reg
    private fun register(){
        binding.push.setOnClickListener {
            val name = binding.inputname.text.toString()
            val username = binding.inputusername.text.toString()
            val email = binding.inputemail.text.toString()
            val password = binding.inputpass.text.toString()
            val phonenum = "+62${binding.inputphonum.text.toString()}"

            //check
            when{
                name.isEmpty() -> binding.inputname.error = "Your name is empty!"
                username.isEmpty() -> binding.inputusername.error = "Your username is empty!"
                email.isEmpty() -> binding.inputemail.error = "Your email is empty!"
                password.isEmpty() -> binding.inputpass.error = "Your password is empty!"
                phonenum.isEmpty() -> binding.inputphonum.error = "Your phone number is empty!"
                password.length < 8 || password.length > 12 -> binding.inputpass.error = "Password must be 8 characters to 12 characters"

                !email.isValidEmail() -> binding.inputemail.error = "Your email is not valid!"
                else -> {
                    authViewModel.register(RegisterModel(name,username,email,phonenum,password)).observe(viewLifecycleOwner){
                        if (it != null){
                            when (it){
                                is Resource.Loading -> loadingDialog?.showDialog()
                                is Resource.Success -> {
                                    loadingDialog?.hideDialog()
                                    Toast.makeText(context, it.data.message, Toast.LENGTH_LONG).show()
                                    val header = activity?.findViewById<ViewPager>(R.id.header)
                                    val signIn = activity?.findViewById<ViewPager>(R.id.viewpager)

                                    header!!.currentItem = 0
                                    signIn!!.currentItem = 0

                                    binding.inputname.setText("")
                                    binding.inputusername.setText("")
                                    binding.inputemail.setText("")
                                    binding.inputpass.setText("")
                                    binding.inputphonum.setText("")
                                }
                                is Resource.Error -> {
                                    loadingDialog?.hideDialog()
                                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //check email
    private fun CharSequence.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}