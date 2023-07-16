package com.example.radiusandroidapp.api

import com.example.radiusandroidapp.model.RadiusAllProduct
import retrofit2.Response
import retrofit2.http.GET

interface RadiusClient {

    @GET("db")
    suspend fun getAllProduct(): Response<RadiusAllProduct>
}