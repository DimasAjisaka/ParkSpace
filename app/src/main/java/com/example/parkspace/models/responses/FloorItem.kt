package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class FloorResponseItem(

	@field:SerializedName("floor_name")
	val floorName: String? = null,

	@field:SerializedName("success")
	val success: Boolean? = false,

	@field:SerializedName("slot_id")
	val slotId: String? = null,

	@field:SerializedName("floor_id")
	val floorId: String? = null,

	@field:SerializedName("slot_name")
	val slotName: Int? = 0,

	@field:SerializedName("status")
	val status: String? = null
)