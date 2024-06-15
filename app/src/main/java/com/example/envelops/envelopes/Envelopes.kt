package com.example.envelops.envelopes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.abs

@Composable
fun EnvelopesScreen(navController: NavController) {
    val envelopes = getEnvelopeData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)

    ) {
        Text(text = "Envelopes", style = MaterialTheme.typography.displaySmall)
        LazyColumn {
            for (envelope in envelopes) {
                item {
                    Spacer(modifier = Modifier.padding(5.dp))
                    Envelope(envelope[0].toString(), 200.00, envelope[1].toString().toDouble())
                }
            }
        }
    }
}

@Composable
fun Envelope(envelopeName: String, budgetAmount: Double, amount: Double) {
    val colour: Color
    val progress: Float
    if (amount >= 0) { // positive progress bar
        progress = ((budgetAmount - amount) / budgetAmount).toFloat()
        colour = Color.Green
    } else { // negative progress bar
        progress = (abs(amount) / budgetAmount).toFloat()
        colour = Color.Red
    }
    Column(
        modifier = Modifier
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(Color.LightGray)
            .padding(10.dp)
    ) {
        Row() {
            Text(text = envelopeName, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "$amount / $budgetAmount",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleSmall
            )
        }
        LinearProgressIndicator(
            progress = progress,
            color = colour,
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 8.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
        )
    }
}

fun getEnvelopeData(): Array<Array<Any?>> {
    val rows = 8
    val cols = 2
    val envelopes: Array<Array<Any?>> = Array(rows) { Array<Any?>(cols) { null } }
    envelopes[0][0] = "Transport"
    envelopes[0][1] = 120.00
    envelopes[1][0] = "Food"
    envelopes[1][1] = 55.00
    envelopes[2][0] = "Shopping"
    envelopes[2][1] = 7.89
    envelopes[3][0] = "Investments"
    envelopes[3][1] = -70.11
    envelopes[4][0] = "Social"
    envelopes[4][1] = -12.13
    envelopes[5][0] = "Social"
    envelopes[5][1] = -12.13
    envelopes[6][0] = "Social"
    envelopes[6][1] = -12.13
    envelopes[7][0] = "Social"
    envelopes[7][1] = -12.13
    return envelopes
}

@Preview(showBackground = true)
@Composable
fun PreviewEnvelopesScreen() {
    EnvelopesScreen(navController = rememberNavController())
}