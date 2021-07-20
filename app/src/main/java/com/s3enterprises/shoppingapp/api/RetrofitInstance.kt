package com.s3enterprises.shoppingapp.api

import com.s3enterprises.shoppingapp.Utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : StoreApi by lazy {
        retrofit.create(StoreApi::class.java)
    }




}