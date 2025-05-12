package com.example.lushlane.ui

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: String
) {
    object Home : BottomNavItem("home", "Home", "🏠")
    object Cart : BottomNavItem("cart", "Cart", "🛒")
    object Profile : BottomNavItem("profile", "Profile", "👤")
}
