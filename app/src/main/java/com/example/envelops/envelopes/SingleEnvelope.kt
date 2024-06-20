package com.example.envelops.envelopes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.navigation.Screens
import com.example.envelops.transactions.Transaction
import com.example.envelops.ui.theme.EnvelopsTheme
import com.example.envelops.ui.theme.Green2
import java.time.LocalDate
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SingleEnvelopeScreen(navController: NavController, envelopesViewModel: EnvelopesViewModel) {
    val envelope = envelopesViewModel.envelope
    if (envelope != null) {
        envelopesViewModel.changeProgressColour(envelope.amount, envelope.budgetedAmount)
    }
    EnvelopsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            if (envelope != null) {
                HeaderSingleEnvelope(envelope.envelopeName, navController)
            }
            Spacer(modifier = Modifier.padding(4.dp))
            if (envelope != null) {
                EnvelopeQuickView(envelope, envelopesViewModel)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Transactions", style = MaterialTheme.typography.headlineMedium)

            LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.secondary)) {
                for (i in 1..3) {
                    item {
                        Spacer(modifier = Modifier.padding(1.dp))
                        Transaction(
                            " Id",
                            LocalDate.now(),
                            "transaction.payee",
                            "transaction.category",
                            77.21,
                            "transaction.memo"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EnvelopeQuickView(envelope: EnvelopeModel, envelopesViewModel: EnvelopesViewModel) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Budgeting period: START DATE - END DATE"
        )
        Text(text = "You're ahead by £8.50")
        Text(text = "Current amount in envelope: ${envelope.amount}")
        Text(text = "Budgeted amount: ${envelope.budgetedAmount}")
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

@Composable
fun HeaderSingleEnvelope(envelopeName: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = {
                navController.navigate(Screens.Envelopes.screen)
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(text = envelopeName, style = MaterialTheme.typography.displaySmall)
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Done icon",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewSingleEnvelopeScreen() {
    val viewModel = viewModel<EnvelopesViewModel>()
    SingleEnvelopeScreen(rememberNavController(), viewModel)
}
