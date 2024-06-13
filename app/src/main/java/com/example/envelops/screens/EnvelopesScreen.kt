package com.example.envelops.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.envelops.navigation.Routes
import kotlin.math.abs

@Composable
fun EnvelopesScreen(navController: NavController, name: String) {
    val rows = 5
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $name!")
        Text(text = "Today the date is Thursday")
        Text(text = "Here are your envelopes")
        for(envelope in envelopes){
            Envelope(envelope[0].toString(),200.00, envelope[1].toString().toDouble())
        }
        Button(
            onClick = {
            navController.navigate(Routes.transactionsScreen)
        }) {
            Text(text = "Go to transaction view")
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
        progress = (abs(amount)  / budgetAmount).toFloat()
        colour = Color.Red
    }
    Column (
        modifier = Modifier
            .padding(15.dp)
            .background(Color.LightGray)
    ){
        Row() {
            Text(text = envelopeName)
            Text(text = "$amount")
            Text(text = "$budgetAmount")
        }
        LinearProgressIndicator(
            progress = progress,
            color = colour,
            modifier = Modifier
                .padding(15.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
        )
    }
}
