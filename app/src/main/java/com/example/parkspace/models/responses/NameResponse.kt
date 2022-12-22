package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class NameResponse(

	@field:SerializedName("success")
	val success: Boolean? = false,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("floor")
	val floor: String? = null,

	@field:SerializedName("slot")
	val slot: Int? = 0,

	@field:SerializedName("timeString")
	val timeString: String? = null,

	@field:SerializedName("total")
	val total: Int? = 0
)