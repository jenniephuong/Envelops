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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.envelops.navigation.Screens
import com.example.envelops.transactions.Transaction
import com.example.envelops.ui.theme.EnvelopsTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SingleEnvelopeScreen(envelopeName: String) {
    EnvelopsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            HeaderSingleEnvelope(envelopeName = envelopeName)
            Spacer(modifier = Modifier.padding(4.dp))
            EnvelopeQuickView()
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
fun EnvelopeQuickView() {
    Card(modifier = Modifier.height(150.dp)) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "This is your envelope. It should show the progress indicator and some fun statistics and insights for the user to see"
        )
    }
}

@Composable
fun HeaderSingleEnvelope(envelopeName: String) {
    val navController = rememberNavController()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = {
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
    SingleEnvelopeScreen("Test Envelope")
}
