package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)