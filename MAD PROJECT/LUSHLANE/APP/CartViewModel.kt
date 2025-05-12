package com.example.lushlane.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.lushlane.ui.Product

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    fun addToCart(product: Product) {
        _cartItems.value = _cartItems.value + product
    }

    fun removeFromCart(product: Product) {
        val index = _cartItems.value.indexOfFirst { it == product }
        if (index != -1) {
            val mutableList = _cartItems.value.toMutableList()
            mutableList.removeAt(index)
            _cartItems.value = mutableList
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }
}
