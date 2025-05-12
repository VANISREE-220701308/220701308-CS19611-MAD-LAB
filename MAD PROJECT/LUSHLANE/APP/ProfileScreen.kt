package com.example.lushlane.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lushlane.R
import com.example.lushlane.viewmodel.CartViewModel
import androidx.compose.runtime.collectAsState


@Composable
fun ProfileScreen(cartViewModel: CartViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Profile Picture
        Image(
            painter = painterResource(id = R.drawable.profile), // <-- Put a profile placeholder image in res/drawable
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Name
        Text(
            text = "Vidyalakshmi E", // your name here
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        Text(
            text = "vidya@example.com",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Divider()

        Spacer(modifier = Modifier.height(24.dp))

        // Some settings or dummy info
        ProfileOptionRow(title = "Orders", description = "View your order history")
        ProfileOptionRow(title = "Account Settings", description = "Manage your account")
        ProfileOptionRow(title = "Help & Support", description = "Get support or FAQs")

        Spacer(modifier = Modifier.height(24.dp))

        Divider()

        Spacer(modifier = Modifier.height(24.dp))

        // Dark Mode Toggle (optional - already in your cartViewModel maybe)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Dark Mode", fontSize = 18.sp)
            Switch(
                checked = cartViewModel.isDarkMode.collectAsState().value,
                onCheckedChange = { cartViewModel.toggleDarkMode() }
            )
        }
    }
}

@Composable
fun ProfileOptionRow(title: String, description: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Text(text = title, fontSize = 20.sp, style = MaterialTheme.typography.titleMedium)
        Text(text = description, fontSize = 14.sp, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
