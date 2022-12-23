package com.example.parkspace.view.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.parkspace.R
import com.example.parkspace.databinding.ActivityEditProfileBinding
import com.example.parkspace.models.responses.ProfileResponse

class EditProfile : AppCompatActivity() {
    private var _binding: ActivityEditProfileBinding? = null
    private val binding get() = _binding!!
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.parceable<ProfileResponse>(data)
        Glide.with(this).load(if (data?.image == "default.png") R.drawable.avatar else "Image Path").circleCrop().into(binding.image)
        data?.name?.let { binding.inputname.setText(it) }
        data?.username?.let { binding.inputusername.setText(it) }
        data?.email?.let { binding.inputemail.setText(it) }
        data?.phoneNumber?.let { binding.inputphonum.setText(it) }

        binding.backarrow.setOnClickListener { finish() }
        binding.save.setOnClickListener { finish() }
    }

    companion object{
        const val data = "Data"
    }

    inline fun <reified T: Parcelable> Intent.parceable(key: String): T? = when{
        Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key,T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}