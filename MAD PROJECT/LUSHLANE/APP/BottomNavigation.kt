package com.example.lushlane.ui

import com.example.lushlane.ui.Product

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.lushlane.R

@Composable
fun LushLaneBottomNavigation(navController: NavController, cartItems: List<Product>) {
    // ...your navigation bar code
    NavigationBar {
        // Home navigation item
        NavigationBarItem(
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "home",
            onClick = { navController.navigate("home") },
            label = { Text("Home") },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "Home") }
        )

        // Cart navigation item
        NavigationBarItem(
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "cart",
            onClick = {
                navController.navigate("cart") {
                    // Clear the back stack and prevent users from navigating back to the Home screen
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            label = { Text("Cart") },
            icon = { Icon(painter = painterResource(id = R.drawable.ic_cart), contentDescription = "Cart") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomNavigation() {
    LushLaneBottomNavigation(navController = rememberNavController(), cartItems = listOf())
}
