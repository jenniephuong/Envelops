package com.example.envelops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.envelops.ui.theme.EnvelopsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnvelopeScreen(name = "Jennie")
        }
    }
}

@Composable
fun EnvelopeScreen(name: String) {
    var envelopes = ArrayList<String>()
    envelopes.add("Travel")
    envelopes.add("Food")
    envelopes.add("Shopping")
    envelopes.add("Social")
    envelopes.add("Investments")
    envelopes.add("Target")



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $name!")
        Text(text = "Today the date is...")
        Text(text = "Here are your envelopes")
        for(envelope in envelopes){
            Envelope(envelope,200.00, 100.00)
        }
    }
}

@Composable
fun Envelope(envelopeName: String, budgetAmount: Double, amount: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(text = envelopeName)
        Text(text = "$amount")

    }
}

@Preview(showBackground = true)
@Composable
fun EnvelopsPreview() {
    EnvelopsTheme {
        EnvelopeScreen("Squishie")
    }
}

// dummy commit