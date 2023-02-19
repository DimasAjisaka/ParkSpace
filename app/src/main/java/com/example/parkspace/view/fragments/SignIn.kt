package com.example.parkspace.view.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.parkspace.R
import com.example.parkspace.databinding.FragmentSignInBinding
import com.example.parkspace.models.responses.data.LoginModel
import com.example.parkspace.utils.LoadingDialog
import com.example.parkspace.utils.Resource
import com.example.parkspace.utils.UserPreverence
import com.example.parkspace.view.activity.ForgotPassActivity
import com.example.parkspace.view.activity.MainActivity
import com.example.parkspace.viewmodels.AuthViewModel

class SignIn : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    //login
    private val authViewModel: AuthViewModel by viewModels()
    //loading
    private var loadingDialog: LoadingDialog? = null
    //session
    private var userPreverence: UserPreverence? = null

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
        userPreverence = UserPreverence(requireActivity())
        loadingDialog = LoadingDialog(requireActivity())

        login()

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

//        binding.push.setOnClickListener {
//            startActivity(Intent(context, MainActivity::class.java))
//        }
    }

    //login
    private fun login(){
        binding.push.setOnClickListener {
            val username = binding.inputusername.text.toString()
            val password = binding.inputpass.text.toString()

            //check
            when{
                username.isEmpty() -> binding.inputusername.error = "Username is empty!"
                password.isEmpty() -> binding.inputpass.error = "Password is empty!"
                password.length < 8 || password.length > 12 -> binding.inputpass.error = "Password must be 8 characters to 12 characters"

                else -> {
                    authViewModel.login(LoginModel(username,password)).observe(viewLifecycleOwner){
                        if (it != null){
                            when(it){
                                is Resource.Loading -> loadingDialog?.showDialog()
                                is Resource.Success -> {
                                    loadingDialog?.hideDialog()
                                    it.data.accessToken?.let { token -> userPreverence?.setToken(token) }
                                    userPreverence?.setLogin(true)
                                    it.data.refreshToken?.let { refreshToken -> userPreverence?.setRefreshToken(refreshToken) }
                                    startActivity(Intent(context, MainActivity::class.java))
                                    activity?.finish()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}