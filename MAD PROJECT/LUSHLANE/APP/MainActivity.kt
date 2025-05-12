package com.example.lushlane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.lushlane.navigation.BottomNavItem
import com.example.lushlane.ui.*
import com.example.lushlane.ui.theme.LushLaneTheme
import com.example.lushlane.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    private val cartViewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LushLaneTheme(darkTheme = cartViewModel.isDarkMode.collectAsState().value) {
                LushLaneApp(cartViewModel)
            }
        }
    }
}

@Composable
fun LushLaneApp(cartViewModel: CartViewModel) {
    val navController = rememberNavController()
    val cartItems by cartViewModel.cartItems.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Cart,
                    BottomNavItem.Profile
                ).forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("splash") {
                SplashScreen(navController)
            }

            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    navController = navController,
                    cartItems = cartItems,
                    onAddToCart = { product -> cartViewModel.addToCart(product) }
                )
            }

            composable(BottomNavItem.Cart.route) {
                CartScreen(
                    cartItems = cartItems,
                    onAddQty = { cartViewModel.addToCart(it) },
                    onRemoveQty = { cartViewModel.removeFromCart(it) },
                    onCheckoutClick = { navController.navigate("checkout") }
                )
            }

            composable(BottomNavItem.Profile.route) {
                ProfileScreen(cartViewModel)
            }

            composable(
                "productDetails/{productName}/{productDescription}/{productPrice}/{productImage}",
                arguments = listOf(
                    navArgument("productName") { type = NavType.StringType },
                    navArgument("productDescription") { type = NavType.StringType },
                    navArgument("productPrice") { type = NavType.StringType },
                    navArgument("productImage") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val productName = backStackEntry.arguments?.getString("productName") ?: ""
                val productDescription = backStackEntry.arguments?.getString("productDescription") ?: ""
                val productPrice = backStackEntry.arguments?.getString("productPrice") ?: ""
                val productImage = backStackEntry.arguments?.getInt("productImage") ?: 0

                ProductDetailsScreen(
                    productName = productName,
                    productPrice = productPrice,
                    productImage = productImage,
                    onAddToCart = { cartViewModel.addToCart(it) }
                )
            }

            composable("checkout") {
                CheckoutScreen(
                    cartViewModel = cartViewModel,
                    navController = navController
                )
            }

            composable("thankyou") {
                ThankYouScreen(
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}
