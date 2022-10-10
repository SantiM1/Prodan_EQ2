package com.example.prodan.network

import com.example.prodan.data.pet
import retrofit2.http.GET

interface NetworkInterface {

    @GET("/data")
    suspend fun getPets() : pet
}