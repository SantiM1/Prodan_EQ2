package com.example.prodan.network

import com.example.prodan.data.pet
import retrofit2.http.GET

interface NetworkInterface {
    // mandamos llamar el GET para obtener el data
    @GET("/data")
    suspend fun getPets() : pet
}