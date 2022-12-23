package com.example.parkspace.utils

import com.example.parkspace.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIConfig {
    companion object {
        fun getAPIService(): APIService{
            val loggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).connectTimeout(100,TimeUnit.SECONDS).readTimeout(100,TimeUnit.SECONDS).build()
            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit.create(APIService::class.java)
        }
    }
}