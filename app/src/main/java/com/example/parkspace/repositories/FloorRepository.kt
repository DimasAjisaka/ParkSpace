package com.example.parkspace.repositories

import com.example.parkspace.utils.APIConfig

class FloorRepository {
    private val client = APIConfig.getAPIService()
    suspend fun parkingSlot(token: String,floor: String) = client.getParkSlot(token,floor)
}