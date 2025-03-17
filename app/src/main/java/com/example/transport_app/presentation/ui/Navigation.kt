package com.example.transport_app.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.transport_app.OrganizationScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable("auth") {
            AuthenticationScreen(
                onRegisterSuccess = { navController.navigate("organization") },
                onLoginSuccess = { navController.navigate("dailyPlan") }
            )
        }
        composable("organization") {
            OrganizationScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("dailyPlan") {
            DailyPlanScreen { routeId ->
                navController.navigate("confirmDelivery/$routeId")
            }
        }
        composable(
            route = "confirmDelivery/{routeId}",
            arguments = listOf(navArgument("routeId") { type = NavType.IntType })
        ) {
            val routeId = it.arguments?.getInt("routeId") ?: -1
            DeliveryConfirmationScreen(
                routeId = routeId,
                onConfirmDelivery = {
                    // Volver atrás
                    navController.popBackStack()
                },
                onRejectDelivery = {
                    // Volver atrás
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
