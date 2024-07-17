package com.rs.littlelemonapp.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rs.littlelemonapp.navigation.Home
import com.rs.littlelemonapp.navigation.Onboarding
import com.rs.littlelemonapp.navigation.Profile

@Composable
fun NavigationComposable(context: Context, navHostController: NavHostController) {
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var startDestination = Onboarding.route

    if (sharedPreferences.getBoolean("userRegistered", false)) {
        startDestination = Home.route
    }

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(Onboarding.route) {
            Onboarding(context = context, navHostController = navHostController)
        }
        composable(Home.route) {
            Home(navHostController)
        }
        composable(Profile.route) {
            Profile(context = context, navHostController = navHostController)
        }
    }
}