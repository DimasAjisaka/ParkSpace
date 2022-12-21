package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("roles")
	val roles: List<String?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)