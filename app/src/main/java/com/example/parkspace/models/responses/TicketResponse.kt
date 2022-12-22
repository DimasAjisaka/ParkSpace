package com.example.parkspace.models.responses

import com.google.gson.annotations.SerializedName

data class TicketResponse(

	@field:SerializedName("TicketResponse")
	val ticketResponse: List<TicketItem>?
)