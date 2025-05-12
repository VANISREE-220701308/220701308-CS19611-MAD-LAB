package com.example.lushlane.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lushlane.viewmodel.CartViewModel

@Composable
fun CheckoutScreen(
    cartViewModel: CartViewModel,
    navController: NavController
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val subtotal = cartItems.sumOf { it.price.filter { c -> c.isDigit() }.toIntOrNull() ?: 0 }
    val total = subtotal // (Add taxes if you want)

    var selectedPaymentMethod by remember { mutableStateOf("UPI") }

    Scaffold(
        topBar = { TopBar("Checkout") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Select Payment Method", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(16.dp))

                PaymentOptions(selectedPaymentMethod) { selectedPaymentMethod = it }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Total Amount: ₹$total", fontSize = 20.sp)
            }

            Button(
                onClick = {
                    navController.navigate("thankyou")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Place Order", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun PaymentOptions(
    selectedMethod: String,
    onMethodSelected: (String) -> Unit
) {
    val methods = listOf("Credit Card", "Debit Card", "UPI", "Net Banking", "Cash on Delivery")

    Column {
        methods.forEach { method ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = (method == selectedMethod),
                    onClick = { onMethodSelected(method) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = method, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
