package com.example.envelops.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.R

@Composable
fun ReportsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(text = "Reports", style = MaterialTheme.typography.displaySmall)
            ReportCard()
            ReportCard()
            ReportCard()
            ReportCard()
        }
    }
}

@Composable
fun ReportCard() {
    Spacer(modifier = Modifier.padding(8.dp))
    Card(modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 10.dp
//        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.inversePrimary),
        content = {
            Row(modifier = Modifier.padding(16.dp)) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null
                    )
                }
                Column {
                    Text(text = "Spending vs. Income", style = MaterialTheme.typography.titleMedium)
                    Text(text = "This is a card")
                }

            }
        })
}


@Preview(showBackground = true,device = Devices.PIXEL_6)
@Composable
fun PreviewReportsScreen() {
    ReportsScreen(navController = rememberNavController())
}