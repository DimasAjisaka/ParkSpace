package com.example.parkspace.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.parkspace.models.data.TicketModel
import com.example.parkspace.models.responses.FloorItem
import com.example.parkspace.models.responses.FloorResponse
import com.example.parkspace.models.responses.TicketItem
import com.example.parkspace.repositories.FloorRepository
import com.example.parkspace.utils.Resource
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class FloorViewModel: ViewModel() {
    private val repository = FloorRepository()
    fun parkingSlots(token: String, floor: String): LiveData<Resource<List<FloorItem>>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.parkingSlot(token,floor)
            emit(Resource.Success(response))
        } catch (e: HttpException){
            emit(Resource.Error(
                try {
                    e.response()?.errorBody()?.string()?.let { JSONObject(it).get("message") }
                } catch (e: JSONException) {
                    e.localizedMessage
                } as String
            ))
        }
    }

    fun ticket(ticketModel: TicketModel, token: String): LiveData<Resource<List<TicketItem>>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.ticket(ticketModel,token)
            emit(Resource.Success(response))
        } catch (e: HttpException){
            emit(Resource.Error(
                try {
                    e.response()?.errorBody()?.string()?.let { JSONObject(it).get("message") }
                } catch (e: JSONException) {
                    e.localizedMessage
                } as String
            ))
        }
    }

}