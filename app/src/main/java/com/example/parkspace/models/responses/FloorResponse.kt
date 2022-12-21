package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class FloorResponse(

//	@field:SerializedName("FloorResponse")
	val floorResponse: List<FloorItem>?
)