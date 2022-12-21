package com.example.parkspace.repositories

import com.example.parkspace.utils.APIConfig

class UserRepository {
    private val client = APIConfig.getAPIService()
    suspend fun getName(token: String) = client.getName(token)

    suspend fun getProfile(token: String) = client.getProfile(token)
}