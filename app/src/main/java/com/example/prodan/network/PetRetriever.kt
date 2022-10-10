package com.example.prodan.network

import com.example.prodan.data.pet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PetRetriever {
    private val networkInterface : NetworkInterface
    companion object {
        var BaseUrl = "https://petsapi2022.herokuapp.com"
    }
    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        networkInterface = retrofit.create(NetworkInterface::class.java)
    }

    suspend fun getPets() : pet {
        return networkInterface.getPets()
    }
}