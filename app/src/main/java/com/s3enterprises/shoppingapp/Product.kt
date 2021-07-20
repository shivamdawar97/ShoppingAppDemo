package com.s3enterprises.shoppingapp

import java.io.Serializable

data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
):Serializable