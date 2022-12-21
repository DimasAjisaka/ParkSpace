package com.example.parkspace.utils

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentActivity
import com.example.parkspace.databinding.LoadingDialogBinding

class LoadingDialog(activity: FragmentActivity) {
    private var loadingDialog: Dialog? = null
    init {
        val binding = LoadingDialogBinding.inflate(activity.layoutInflater)
        loadingDialog = Dialog(activity)
        loadingDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadingDialog?.setContentView(binding.root)
        loadingDialog?.setCancelable(false)
    }
    fun showDialog(){ loadingDialog?.show() }
    fun hideDialog(){ loadingDialog?.dismiss() }
}