package com.example.envelops.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TransactionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "This is the transactions screen")
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Button(onClick = { /*TODO*/ }) {

            }
            Button(onClick = { /*TODO*/ }) {

            }
            Button(onClick = { /*TODO*/ }) {

            }
            Button(onClick = { /*TODO*/ }) {

            }
        }
        for (i in 0..20) {
            Transaction()
        }
    }
}

@Composable
fun Transaction() {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .background(Color.Gray)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)

        ) {
            Text(text = "date")
            Text(text = "company")
            Text(text = "category")
            Text(text = "amount")
        }
    }
}