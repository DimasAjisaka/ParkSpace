package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class NameResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null
)