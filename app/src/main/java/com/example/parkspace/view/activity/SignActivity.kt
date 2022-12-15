package com.example.parkspace.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.parkspace.adapters.SignHeaderAdapter
import com.example.parkspace.adapters.SignTabPagerAdapter
import com.example.parkspace.databinding.ActivitySignBinding
import com.google.android.material.tabs.TabLayout
import java.io.File


class SignActivity : AppCompatActivity() {
    private var _binding: ActivitySignBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentPhotoPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       _binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //check
        if (checkSelfPermission(REQUIRED_PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) requestPermissions(REQUIRED_PERMISSIONS,1)

        //bar
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val view = getWindow().decorView

        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                window.statusBarColor = Color.parseColor("#313F4A")
                view.systemUiVisibility = view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                window.statusBarColor = Color.parseColor("#AAE5FF")
                view.systemUiVisibility = view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

        binding.tablay.newTab().let { binding.tablay.addTab(it.setText("Sign In")) }
        binding.tablay.newTab().let { binding.tablay.addTab(it.setText("Sign Up")) }
        binding.tablay.tabGravity = TabLayout.GRAVITY_FILL

        val adapter =
            binding.tablay.tabCount.let { SignTabPagerAdapter(this, supportFragmentManager, it) }
        val adapterHeader =
            binding.tablay.tabCount.let { SignHeaderAdapter(this, supportFragmentManager, it) }
        binding.header.adapter = adapterHeader
        binding.viewpager.adapter = adapter
        binding.header.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablay))
        binding.viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablay))
        binding.tablay.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.header.currentItem = tab?.position ?: 0
                binding.viewpager.currentItem = tab?.position ?: 0
                when (tab?.position){
                    0 -> {
                        binding.header.currentItem = 0
                        binding.viewpager.currentItem = 0
                    }
                    1 -> {
                        binding.header.currentItem = 1
                        binding.viewpager.currentItem = 1
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    //companion
    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        private const val REQUEST_CODE_PERMISSIONS=10
        }

    // req permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!checkPermissionCamera()) {
                Toast.makeText(this, "Tidak mendapatkan izin", Toast.LENGTH_SHORT).show()
                finish()
            }
            }
        }

    //camera
    private fun checkPermissionCamera(): Boolean {
        val check = ContextCompat.checkSelfPermission(this, REQUIRED_PERMISSIONS[0])
        return check == PackageManager.PERMISSION_GRANTED
        }

    //take
//    @SuppressLint("QueryPermissionsNeeded")
//    private fun takePicture() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.resolveActivity(packageManager)
//        createTempFile(application).also {
//            val photoURI: Uri = FileProvider.getUriForFile(this, "com.example.parkspace", it)
//            currentPhotoPath = it.absolutePath
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//            launcherIntentCamera.launch(intent)
//            }
//        }

    //launch
    private val launcherIntentCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            val result = BitmapFactory.decodeFile(myFile.path)
//            binding.image.setImageBitmap(result)
//            bitmap = result
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}