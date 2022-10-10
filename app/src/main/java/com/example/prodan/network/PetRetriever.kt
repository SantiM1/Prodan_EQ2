package com.example.prodan.network

import com.example.prodan.data.pet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PetRetriever {
    // creamos una instania de NetworkInterface
    private val networkInterface : NetworkInterface
    // definimos el BaseUrl
    companion object {
        var BaseUrl = "https://petsapi2022.herokuapp.com"
    }
    init {
        // mandamos llamar el convertidor de JSON
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Creamos a interfaz de red
        networkInterface = retrofit.create(NetworkInterface::class.java)
    }

    suspend fun getPets() : pet {
        // mandamos llamar a la función de GET
        return networkInterface.getPets()
    }
}