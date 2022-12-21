package com.example.parkspace.utils

import android.util.Base64
import okio.Utf8
import java.io.UnsupportedEncodingException

class JWTUtils {
    companion object{
        fun decode(token: String): String?{
            return try {
                val splits: List<String> = token.split(".")
                getJSON(splits[1])
            } catch (e: UnsupportedEncodingException){ e.toString() }
        }
        private fun getJSON(stringEncoded: String): String{
            val decodeByte: ByteArray = Base64.decode(stringEncoded, Base64.URL_SAFE)
            return String(decodeByte, Charsets.UTF_8)
        }
    }
}