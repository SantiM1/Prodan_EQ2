package com.example.prodan.network

import com.example.prodan.data.pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkInterface {
    @GET("/data")
    suspend fun getPets() : pet
    @POST("/postul")
    suspend fun addPetRequest(@Body userReq : UsersRequest)
}