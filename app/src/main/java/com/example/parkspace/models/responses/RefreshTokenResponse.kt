package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)