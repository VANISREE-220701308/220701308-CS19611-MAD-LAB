package com.example.lushlane.ui
import androidx.compose.foundation.clickable

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lushlane.R

@Composable
fun ProductListScreen(
    categoryName: String,
    navController: NavController,
    onAddToCart: (Product) -> Unit
) {
    val context = LocalContext.current

    val allProducts = listOf(
        Product("Red Dress", "Stylish and comfortable", "₹799", R.drawable.reddress, "Dresses"),
        Product("Summer Dress", "Light breezy dress", "₹699", R.drawable.reddress, "Dresses"),
        Product("Casual Shirt", "Everyday casual wear", "₹499", R.drawable.shirt, "Shirts"),
        Product("Formal Shirt", "Perfect for office", "₹899", R.drawable.shirt, "Shirts"),
        Product("Sneakers", "Sporty trendy shoes", "₹999", R.drawable.sneakers, "Shoes"),
        Product("Running Shoes", "Built for speed", "₹1499", R.drawable.sneakers, "Shoes"),
        Product("Sport Watch", "Durable waterproof watch", "₹4999", R.drawable.watch, "Watches"),
        Product("Classic Watch", "Elegant wristwatch", "₹2999", R.drawable.watch, "Watches")
    )

    val filteredProducts = allProducts.filter { it.category == categoryName }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)
    ) {
        Text(
            text = "Products - $categoryName",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredProducts) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable {
                            navController.navigate(
                                "productDetails/${product.title}/${product.description}/${product.price}/${product.imageRes}"
                            )
                        },
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = product.imageRes),
                            contentDescription = product.title,
                            modifier = Modifier.size(80.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = product.title, style = MaterialTheme.typography.titleSmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = product.price, style = MaterialTheme.typography.bodySmall)

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                onAddToCart(product)
                                Toast.makeText(context, "✅ Added to Cart!", Toast.LENGTH_SHORT).show()
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Add to Cart", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
