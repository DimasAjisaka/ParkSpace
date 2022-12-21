package com.example.parkspace.utils

import android.content.Context
import android.content.SharedPreferences

class UserPreverence(context: Context) {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    init {
        sharedPreferences = context.getSharedPreferences("app key",0)
        editor = sharedPreferences?.edit()
        editor?.apply()
    }

    fun setToken(token: String){
        editor?.putString("token", token)
        editor?.commit()
    }

    fun setLogin(isLogin: Boolean){
        editor?.putBoolean("is login", isLogin)
        editor?.commit()
    }

    fun getToken() = sharedPreferences?.getString("token", null)

    fun getLogin() = sharedPreferences?.getBoolean("is login", false)

    fun setRefreshToken(refresToken: String) {
        editor?.putString("refresh token", refresToken)
        editor?.commit()
    }
    fun getRefreshToken() = sharedPreferences?.getString("refresh token", null)
}