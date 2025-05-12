package com.example.lushlane.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lushlane.R

@Composable
fun HomeScreen(
    navController: NavController,
    cartItems: List<Product>,
    onAddToCart: (Product) -> Unit
) {
    val products = listOf(
        Product("Red Dress", "Stylish and comfortable", "₹799", R.drawable.reddress, "Dresses"),
        Product("Casual Shirt", "Lightweight casual shirt", "₹499", R.drawable.shirt, "Shirts"),
        Product("Sneakers", "Trendy sports sneakers", "₹999", R.drawable.sneakers, "Shoes"),
        Product("Sport Watch", "Waterproof digital watch", "₹4999", R.drawable.watch, "Watches"),
        Product("Elegant Skirt", "Flowy and chic skirt", "₹899", R.drawable.skirt, "Dresses"),
        Product("Denim Jacket", "Classic blue jacket", "₹1499", R.drawable.jacket, "Shirts"),
        Product("Running Shoes", "Perfect for sports", "₹1299", R.drawable.shoesss, "Shoes"),
        Product("Luxury Watch", "Elegant premium watch", "₹7999", R.drawable.watch_women, "Watches")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TopBar(screenTitle = "Home") // ✅ Added TopBar

        Spacer(modifier = Modifier.height(8.dp))

        SearchBar()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Featured Products",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onAddToCart = { onAddToCart(product) },
                    onClickProduct = {
                        navController.navigate(
                            "productDetails/${product.title}/${product.description}/${product.price}/${product.imageRes}"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    var searchQuery by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        label = { Text("Search products...") },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun ProductCard(
    product: Product,
    onAddToCart: () -> Unit,
    onClickProduct: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickProduct() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onAddToCart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Add to Cart", fontSize = 12.sp)
            }
        }
    }
}

data class Category(
    val name: String,
    val imageRes: Int
)
