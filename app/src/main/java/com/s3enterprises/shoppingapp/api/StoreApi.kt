package com.s3enterprises.shoppingapp.api

import com.s3enterprises.shoppingapp.Product
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {

    @GET("/products")
    suspend fun getAllProducts():Response<List<Product>>
}