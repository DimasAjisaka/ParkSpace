package com.example.parkspace.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.parkspace.R
import com.example.parkspace.databinding.ActivityProfilBinding
import com.example.parkspace.models.data.LogoutModel
import com.example.parkspace.models.responses.ProfileResponse
import com.example.parkspace.utils.LoadingDialog
import com.example.parkspace.utils.Resource
import com.example.parkspace.utils.UserPreverence
import com.example.parkspace.viewmodels.AuthViewModel
import com.example.parkspace.viewmodels.UserViewModel

class ProfilActivity : AppCompatActivity() {
    private var _binding: ActivityProfilBinding? = null
    private val binding get() = _binding!!
    private var loadingDialog: LoadingDialog? = null
    private val authViewModel: AuthViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingDialog = LoadingDialog(this)
        val userPreverence = UserPreverence(this)

        setUpProfile(userPreverence.getToken()!!)

        binding.backarrow.setOnClickListener { finish() }
        binding.logout.setOnClickListener {
            userPreverence.setLogin(false)
            userPreverence.setToken("")
            authViewModel.logout(LogoutModel(userPreverence.getRefreshToken())).observe(this){
                if (it != null){
                    when(it){
                        is Resource.Loading -> loadingDialog?.showDialog()
                        is Resource.Success -> {
                            loadingDialog?.hideDialog()
                            Toast.makeText(this,it.data.message,Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, SignActivity::class.java))
                            finish()
                        }
                        is Resource.Error -> {
                            loadingDialog?.hideDialog()
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        binding.about.setOnClickListener { startActivity(Intent(this, AboutActivity::class.java)) }
    }

    private fun setUpProfile(token: String){
        userViewModel.getProfile(token).observe(this){
            if (it != null){
                when(it) {
                    is Resource.Loading -> loadingDialog?.showDialog()
                    is Resource.Success -> {
                        loadingDialog?.hideDialog()
                        val response = it.data
                        Glide.with(this).load(if (response.image == "default.png") R.drawable.avatar else "Image Path").circleCrop().into(binding.image)
                        binding.name.text = response.name
                        binding.email.text = response.email
                        parcelData(ProfileResponse(response.image, response.phoneNumber?.drop(3), response.address, response.success, response.name, response.email, response.username ))
                    }
                    is Resource.Error -> {
                        loadingDialog?.hideDialog()
                        Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun parcelData(profileResponse: ProfileResponse){
        binding.edit.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            intent.putExtra(EditProfile.data,profileResponse)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}