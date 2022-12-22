package com.example.parkspace.utils

import com.example.parkspace.models.data.*
import com.example.parkspace.models.responses.*
import retrofit2.http.*

interface APIService {
    @POST ("api/auth/register") suspend fun register(@Body registerModel: RegisterModel): RegisterResponse
    @POST ("api/auth/login") suspend fun login(@Body loginModel: LoginModel): LoginResponse
    @GET("api/user") suspend fun getName(@Header("x-access-token") token: String): NameResponse
    @POST("api/auth/logout") suspend fun logout(@Body logoutModel: LogoutModel): LogoutResponse
    @GET("api/profile") suspend fun getProfile(@Header("x-access-token") token: String): ProfileResponse
    @POST("api/auth/refreshtoken") suspend fun refreshToken(@Body refreshTokenModel: RefreshTokenModel): RefreshTokenResponse
    @GET("api/parkingSlot/{floor}") suspend fun getParkSlot(@Header("x-access-token") token: String, @Path("floor") floor: String): List<FloorItem>
    @POST("api/parking") suspend fun ticket(@Body ticketModel: TicketModel, @Header("x-access-token") token: String): List<TicketItem>
}