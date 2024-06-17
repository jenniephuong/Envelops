package com.example.envelops.transactions

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.ui.theme.EnvelopsTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun TransactionsScreen(navController: NavController) {
    EnvelopsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Header()
            FilterChipGroup()
            Spacer(modifier = Modifier.padding(2.dp))
            LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                for (i in 0..20) {
                    var hasMemo = false
                    if (i % 2 == 0) {
                        hasMemo = true
                    }
                    item {
                        Spacer(modifier = Modifier.padding(2.dp))
                        Transaction("20/04", "AndroidStudio", "Subscriptions", -15.71, hasMemo)
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = "Transactions", style = MaterialTheme.typography.displaySmall)
        IconButton(onClick = {
            Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()
        }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Done icon",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipGroup() {
    var selectedChip by remember { mutableStateOf<String?>(null) }
    val chipLabels = listOf("Day", "Week", "Month", "Year")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        chipLabels.forEach { label ->
            FilterChip(
                selected = selectedChip == label,
                onClick = {
                    selectedChip = if (selectedChip == label) null else label
                },
                label = { Text(label) },
                trailingIcon = if (selectedChip == label) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
}


@Composable
fun Transaction(
    date: String,
    payee: String,
    category: String,
    amount: Double,
    hasMemo: Boolean
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = date,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(text = "2024")
            }
            Column {
                Text(text = payee)
                Text(text = category)
                if (hasMemo) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),

                            )
                        Text(text = "memo 1145678iop1")
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End

            ) {
                Text(text = amount.toString())
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_6)
@Composable
fun PreviewTransactionScreen() {
    TransactionsScreen(navController = rememberNavController())
}