package com.example.lushlane.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lushlane.viewmodel.CartViewModel
import com.airbnb.lottie.compose.*
import com.example.lushlane.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue

@Composable
fun ThankYouScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConfettiAnimation()

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Thank You for your Purchase!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                cartViewModel.clearCart()
                navController.navigate(BottomNavItem.Home.route) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Continue Shopping", fontSize = 18.sp)
        }
    }
}

@Composable
fun ConfettiAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.confetti))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier.size(250.dp)
    )
}
