package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class TicketItem(

	@field:SerializedName("parking_code")
	val parkingCode: String? = null,

	@field:SerializedName("parking_status")
	val parkingStatus: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("slot_id")
	val slotId: String? = null,

	@field:SerializedName("floor_id")
	val floorId: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)