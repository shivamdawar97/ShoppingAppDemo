package com.s3enterprises.shoppingapp

import com.s3enterprises.shoppingapp.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getProducts():Response<List<Product>>{
        return RetrofitInstance.api.getAllProducts()
    }
}