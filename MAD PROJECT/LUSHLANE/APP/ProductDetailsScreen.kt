package com.example.lushlane.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lushlane.R

@Composable
fun ProductDetailsScreen(
    productName: String,
    productPrice: String,
    productImage: Int,
    onAddToCart: (Product) -> Unit
) {
    val context = LocalContext.current
    var selectedSize by remember { mutableStateOf("M") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Product Image
        Image(
            painter = painterResource(id = productImage),
            contentDescription = productName,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(MaterialTheme.colorScheme.surface),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Name
        Text(
            text = productName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Product Price
        Text(
            text = productPrice,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Size Selection
        Text(
            text = "Select Size",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        SizeSelector(selectedSize) { size ->
            selectedSize = size
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Product Description
        Text(
            text = "Product Description",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "This stylish $productName is made from premium materials, perfect for any occasion. Choose your size and add to cart!",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Add to Cart Button
        Button(
            onClick = {
                onAddToCart(
                    Product(
                        title = productName,
                        description = "Premium quality $productName for all occasions",
                        price = productPrice,
                        imageRes = productImage,
                        category = "General" // Default category
                    )
                )
                Toast.makeText(context, "✅ Added to Cart!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Add to Cart", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun SizeSelector(selectedSize: String, onSizeSelected: (String) -> Unit) {
    val sizes = listOf("S", "M", "L", "XL")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        sizes.forEach { size ->
            Button(
                onClick = { onSizeSelected(size) },
                colors = if (size == selectedSize) {
                    ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                } else {
                    ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            ) {
                Text(text = size, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}
