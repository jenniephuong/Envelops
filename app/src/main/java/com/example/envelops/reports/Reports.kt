package com.example.envelops.reports

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.R
import com.example.envelops.ui.theme.EnvelopsTheme

@Composable
fun ReportsScreen(navController: NavController) {
    EnvelopsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(text = "Reports", style = MaterialTheme.typography.displaySmall)
            LazyColumn {
                items(6) {
                    ReportCard()
                }
            }
        }
    }
}

@Composable
fun ReportCard() {
    Spacer(modifier = Modifier.padding(8.dp))
    Card(modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
//            border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondaryContainer),
        content = {
            Row(modifier = Modifier.padding(16.dp)) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Spending vs. Income",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(text = "This is a card")
                }

            }
        })
}

@Preview(showBackground = true, device = Devices.PIXEL_6)
@Composable
fun PreviewReportsScreen() {
    ReportsScreen(navController = rememberNavController())
}