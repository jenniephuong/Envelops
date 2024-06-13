package com.example.envelops.navigation

sealed class Screens(val screen: String) {
    data object Envelopes : Screens("envelopes")
    data object Transactions : Screens("transactions")
    data object Reports : Screens("reports")
    data object Account : Screens("account")
    data object NewTransaction : Screens("newTransaction")
    data object Settings : Screens("settings")
}