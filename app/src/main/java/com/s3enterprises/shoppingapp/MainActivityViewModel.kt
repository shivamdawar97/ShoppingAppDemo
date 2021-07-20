package com.s3enterprises.shoppingapp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val repository = Repository()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val products = MutableLiveData<List<Product>>()
    val cartCount = MutableLiveData<Int>().apply { value = 0 }
    val cart = ArrayList<Product>()
    val cartTotal = MediatorLiveData<Float>().apply {
        addSource(cartCount){
            var total = 0.0
            cart.forEach { p ->
                total+=p.price
            }
            value = total.toFloat()
        }
    }

    init {
        loadProducts()
    }

    private fun loadProducts() = viewModelScope.launch {
        isLoading.value = true
        val response = repository.getProducts()
        if(response.isSuccessful)
            products.value = response.body()
        isLoading.value = false
    }

    fun addToCart(p:Product){
        cart.add(p)
        cartCount.value = cartCount.value!! + 1
    }

    fun removeFromCart(position:Int){
        cart.removeAt(position)
        cartCount.value = cartCount.value!! - 1
    }

    fun clearCart(){
        cart.clear()
        cartCount.value = 0
    }

    fun placeOrder() {
        clearCart()
    }

}