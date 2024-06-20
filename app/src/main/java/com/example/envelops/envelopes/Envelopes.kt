package com.example.envelops.envelopes

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.navigation.Screens
import com.example.envelops.transactions.TransactionsViewModel
import com.example.envelops.ui.theme.EnvelopsTheme
import java.time.LocalDate
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EnvelopesScreen(navController: NavController, envelopesViewModel: EnvelopesViewModel) {
    envelopesViewModel.addMockData()
    EnvelopsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)

        ) {
            Header()
            LazyColumn {
                for (envelope in envelopesViewModel.envelopesArray) {
                    item {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Envelope(envelope, navController, envelopesViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Envelope(
    envelope: EnvelopeModel,
    navController: NavController,
    envelopesViewModel: EnvelopesViewModel
) {
    envelopesViewModel.changeProgressColour(envelope.amount, envelope.budgetedAmount)
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp)
            .clickable {
                envelopesViewModel.packageEnvelope(envelope)
                navController.navigate(Screens.SingleEnvelope.screen)
            }
    ) {
        Row() {
            Text(
                text = envelope.envelopeName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${envelope.amount} / ${envelope.budgetedAmount}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleSmall
            )
        }
        LinearProgressIndicator(
            progress = envelopesViewModel.progress,
            color = envelopesViewModel.colour,
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 8.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Header() {
    val viewModel = viewModel<EnvelopesViewModel>()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = "Envelopes", style = MaterialTheme.typography.displaySmall)
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = {
                viewModel.addEnvelope("Emergency Fund", 150.00, 0.0, "Monthly")
            }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewEnvelopesScreen() {
    val viewModel = viewModel<EnvelopesViewModel>()
    EnvelopesScreen(navController = rememberNavController(), viewModel)
}