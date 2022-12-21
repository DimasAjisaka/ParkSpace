package com.example.parkspace.view.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.parkspace.R
import com.example.parkspace.adapters.OnBoardingSliderAdapter
import com.example.parkspace.databinding.ActivityOnBoardingBinding
import com.example.parkspace.view.onboarding.OnBoarding
import java.io.File

class OnBoardingActivity : AppCompatActivity() {
    private var _binding: ActivityOnBoardingBinding? = null
    private val binding get() = _binding!!
    private val onBoardingSliderAdapter = OnBoardingSliderAdapter(
        listOf(
            OnBoarding(
                "Find Your Parking Space",
            "SEARCH and FIND to GET KNOW",
                R.drawable.find_park_icon
            ),
            OnBoarding(
                "Book Your Parking Space",
                "BOOK your parking area",
                R.drawable.booking_icon
            ),
            OnBoarding(
                "Scan Parking Ticket",
                "SEARCH and FIND available parking area",
                R.drawable.scan_icon
            ),
            OnBoarding(
                "Start To Find Your Parking Space",
                "",
                R.drawable.pslogo
            )
        )
    )
    private lateinit var currentPhotoPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //check
        if (checkSelfPermission(REQUIRED_PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) requestPermissions(REQUIRED_PERMISSIONS,2)

        binding.sliderView.adapter = onBoardingSliderAdapter
        setupIndicators()
        setCurrentIndicators(0)
        binding.sliderView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
                if (position == 3){
                    "Start".also { binding.buttonNext.text = it }
                } else{
                    "Next".also { binding.buttonNext.text = it }
                }
            }
        })
        binding.buttonNext.setOnClickListener{
            if (binding.sliderView.currentItem + 1 < onBoardingSliderAdapter.itemCount){
                binding.sliderView.currentItem += 1
//                if (binding.sliderView.currentItem == onBoardingSliderAdapter.itemCount-1){
//                    "Start".also { binding.buttonNext.text = it }
//                }
//                else{
//                    "Next".also { binding.buttonNext.text = it }
//                }
            }else{
                Intent(applicationContext, SignActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(onBoardingSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicators(index: Int){
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = binding.indicatorContainer.get(i) as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

    //companion
    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
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
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val myFile = File(currentPhotoPath)
            val result = BitmapFactory.decodeFile(myFile.path)
//            binding.image.setImageBitmap(result)
//            bitmap = result
        }
    }

    //location service
    private fun isLocationEnable(): Boolean{
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}