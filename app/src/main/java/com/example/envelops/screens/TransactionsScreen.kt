package com.example.envelops.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.envelops.navigation.Routes

@Composable
fun TransactionsScreen(navController: NavController, name: String) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $name!")
        Text(text = "This is the transactions screen")

        Button(onClick = {
            navController.navigate(Routes.envelopesScreen)
        }) {
            Text(text = "Go to envelopes")
        }
    }
}