package com.example.parkspace.repositories

import com.example.parkspace.models.responses.data.LoginModel
import com.example.parkspace.models.responses.data.LogoutModel
import com.example.parkspace.models.responses.data.RefreshTokenModel
import com.example.parkspace.models.responses.data.RegisterModel
import com.example.parkspace.utils.APIConfig

class AuthRepository {
    private val client = APIConfig.getAPIService()
    suspend fun register(registerModel: RegisterModel) = client.register(registerModel)

    suspend fun login(loginModel: LoginModel) = client.login(loginModel)

    suspend fun logout(logoutModel: LogoutModel) = client.logout(logoutModel)

    suspend fun refreshToken(refreshTokenModel: RefreshTokenModel) = client.refreshToken(refreshTokenModel)
}