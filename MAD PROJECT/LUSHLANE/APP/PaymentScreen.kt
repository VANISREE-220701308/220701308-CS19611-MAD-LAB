package com.example.lushlane.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PaymentScreen(navController: NavController) {
    val paymentMethods = listOf("Credit Card", "Debit Card", "UPI", "Net Banking", "Cash on Delivery")
    var selectedMethod by remember { mutableStateOf(paymentMethods[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Choose Payment Method", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            paymentMethods.forEach { method ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (method == selectedMethod),
                            onClick = { selectedMethod = method }
                        )
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = (method == selectedMethod),
                        onClick = { selectedMethod = method }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(method)
                }
            }
        }

        Button(
            onClick = { navController.navigate("thankyou") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Pay Now", style = MaterialTheme.typography.titleLarge)
        }
    }
}
