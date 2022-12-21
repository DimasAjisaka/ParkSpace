package com.example.parkspace.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.parkspace.models.responses.NameResponse
import com.example.parkspace.models.responses.ProfileResponse
import com.example.parkspace.repositories.UserRepository
import com.example.parkspace.utils.Resource
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class UserViewModel: ViewModel() {
    private val repository = UserRepository()
    fun getName(token: String): LiveData<Resource<NameResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.getName(token)
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

    fun getProfile(token: String): LiveData<Resource<ProfileResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.getProfile(token)
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