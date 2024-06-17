package com.example.envelops.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.navigation.Screens
import com.example.envelops.ui.theme.EnvelopsTheme
import com.example.envelops.ui.theme.LocalSpacing
import com.example.envelops.ui.theme.spacing

@Composable
fun AccountScreen(navController: NavController) {
    EnvelopsTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(15.dp)
            ) {
                Text(text = "Account", style = MaterialTheme.typography.displaySmall)
                Button(
                    onClick = {
                        navController.navigate(Screens.Settings.screen)
                    },
                ) {
                    Text(text = "This is a button")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountScreen(navController = rememberNavController())
}