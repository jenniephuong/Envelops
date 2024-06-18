package com.example.envelops.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.envelops.account.AccountScreen
import com.example.envelops.envelopes.EnvelopesScreen
import com.example.envelops.envelopes.SingleEnvelopeScreen
import com.example.envelops.newTransaction.NewTransactionScreen
import com.example.envelops.reports.ReportsScreen
import com.example.envelops.settings.Settings
import com.example.envelops.transactions.TransactionsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screens.Envelopes.screen,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screens.Envelopes.screen) {
            EnvelopesScreen(navController)
        }
        composable(Screens.Transactions.screen) {
            TransactionsScreen(navController)
        }
        composable(Screens.Reports.screen) {
            ReportsScreen(navController)
        }
        composable(Screens.Account.screen) {
            AccountScreen(navController)
        }
        composable(Screens.NewTransaction.screen) {
            NewTransactionScreen(navController)
        }
        composable(Screens.Settings.screen) {
            Settings(navController)
        }
        composable(Screens.SingleEnvelope.screen) { backStackEntry ->
            val envelopeID = backStackEntry.arguments?.getString("envelopeID")
            if (envelopeID != null) {
                SingleEnvelopeScreen(envelopeID)
            }
        }
    }
}