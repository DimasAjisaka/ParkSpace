package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("address")
	val address: Any? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)