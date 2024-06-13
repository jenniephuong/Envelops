package com.example.envelops.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.envelops.navigation.Screens

@Composable
fun AccountScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Hello")
            Text(text = "This is the accounts screen")
            Button(onClick = {
                navController.navigate(Screens.Settings.screen)
            }) {

            }
        }
    }
}