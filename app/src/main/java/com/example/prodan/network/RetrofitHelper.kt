package com.example.prodan.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://petsapi2022.herokuapp.com"

    fun getInstance(): Retrofit {

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}