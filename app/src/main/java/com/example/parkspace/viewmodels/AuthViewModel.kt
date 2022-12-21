package com.example.parkspace.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.parkspace.models.data.LoginModel
import com.example.parkspace.models.data.LogoutModel
import com.example.parkspace.models.data.RefreshTokenModel
import com.example.parkspace.models.data.RegisterModel
import com.example.parkspace.models.responses.LoginResponse
import com.example.parkspace.models.responses.LogoutResponse
import com.example.parkspace.models.responses.RefreshTokenResponse
import com.example.parkspace.models.responses.RegisterResponse
import com.example.parkspace.repositories.AuthRepository
import com.example.parkspace.utils.Resource
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class AuthViewModel: ViewModel() {
    private val repository = AuthRepository()
    fun register(registerModel: RegisterModel): LiveData<Resource<RegisterResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.register(registerModel)
            emit(Resource.Success(response))
        } catch (e: HttpException){ e.localizedMessage?.let { Resource.Error(it) }?.let { emit(it) } }
    }

    fun login(loginModel: LoginModel): LiveData<Resource<LoginResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.login(loginModel)
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

    fun logout(logoutModel: LogoutModel): LiveData<Resource<LogoutResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.logout(logoutModel)
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

    fun refreshToken(refreshTokenModel: RefreshTokenModel): LiveData<Resource<RefreshTokenResponse>> = liveData {
        emit(Resource.Loading)
        try {
            val response = repository.refreshToken(refreshTokenModel)
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