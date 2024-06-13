package com.example.envelops.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.envelops.screens.EnvelopesScreen
import com.example.envelops.screens.TransactionsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.envelopesScreen) {
        composable(Routes.envelopesScreen) {
            EnvelopesScreen(navController, "Jennie")
        }
        composable(Routes.transactionsScreen) {
            TransactionsScreen(navController, "Squishie")
        }
    }
}