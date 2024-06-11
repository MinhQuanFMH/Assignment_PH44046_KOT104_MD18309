package com.example.asmkot_ph44046.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.asmkot_ph44046.ui.screens.CartScreen
import com.example.asmkot_ph44046.ui.screens.CheckOutScreen
import com.example.asmkot_ph44046.ui.screens.HomeScreen
import com.example.asmkot_ph44046.ui.screens.ItemProduct
import com.example.asmkot_ph44046.ui.screens.NotificationScreen
import com.example.asmkot_ph44046.ui.screens.SignInScreen
import com.example.asmkot_ph44046.ui.screens.SignUpScreen
import com.example.asmkot_ph44046.ui.screens.WelcomeScreen


@Composable
fun ScreenNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable(Screens.welcome) {
            WelcomeScreen(navController = navController)
        }
        composable(Screens.signin) {
            SignInScreen(navController = navController)
        }
        composable(Screens.signUp) {
            SignUpScreen(navController = navController)
        }
        composable(Screens.home) {
            HomeScreen(navController = navController)
        }
        composable("${Screens.itemProduct}/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")
            ItemProduct(
                navController = navController,
                id = id ?: ""
            )
        }
        composable(Screens.cart) {
            CartScreen(navController = navController)
        }
        composable(Screens.checkOut) {
            CheckOutScreen(navController = navController)
        }
        composable(Screens.notification) {
            NotificationScreen(navController = navController)
        }
    }

}