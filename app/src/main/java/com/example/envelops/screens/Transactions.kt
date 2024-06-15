package com.example.envelops.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TransactionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {
        Text(text = "Transactions", style = MaterialTheme.typography.displaySmall)
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "All")
//            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Day")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Week")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Month")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Year")
            }
        }
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = "Custom")
//        }
        for (i in 0..20) {
            var hasMemo = false
            if (i % 2 == 0) {
                hasMemo = true
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Transaction("TODAY", "AndroidStudio", "Subscriptions", -15.7, hasMemo)
        }
    }
}

@Composable
fun Transaction(
    date: String,
    company: String,
    category: String,
    amount: Double,
    hasMemo: Boolean
) {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .height(50.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)

        ) {
            Text(text = date)
            Column {
                Text(text = company)
                Text(text = category)
            }
            Text(text = amount.toString())
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End

            ) {
                if (hasMemo) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                    )
                }
                Icon(
                    Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransactionScreen() {
    TransactionsScreen(navController = rememberNavController())
}