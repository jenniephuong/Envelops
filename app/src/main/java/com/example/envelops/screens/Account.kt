package com.example.envelops.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.navigation.Screens

@Composable
fun AccountScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(15.dp)
        ) {
            Text(text = "Account", style = MaterialTheme.typography.displaySmall)
            Button(onClick = {
                navController.navigate(Screens.Settings.screen)
            }) {
                Text(text = "This is a button")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountScreen(navController = rememberNavController())
}