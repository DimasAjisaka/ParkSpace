package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("success")
	val success: Boolean? = false,

	@field:SerializedName("message")
	val message: String? = null
)