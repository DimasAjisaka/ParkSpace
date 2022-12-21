package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class NameResponse(

	@field:SerializedName("success")
	val success: Boolean? = false,

	@field:SerializedName("name")
	val name: String? = null
)