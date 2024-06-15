package com.example.envelops.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun TransactionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Header()
        FilterChipGroup()
        LazyColumn {
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

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = "Transactions", style = MaterialTheme.typography.displaySmall)
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Done icon",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun FilterChipGroup() {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        FilterChip(
            chipName = "Day",
            modifier = Modifier
                .weight(1f)


        )
        Spacer(modifier = Modifier.padding(2.dp))
        FilterChip(
            chipName = "Week",
            modifier = Modifier
                .weight(1f)


        )
        Spacer(modifier = Modifier.padding(2.dp))
        FilterChip(
            chipName = "Month",
            modifier = Modifier
                .weight(1f)

        )
        Spacer(modifier = Modifier.padding(2.dp))
        FilterChip(
            chipName = "Year",
            modifier = Modifier
                .weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChip(chipName: String, modifier: Modifier) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        modifier = modifier,
        onClick = { selected = !selected },
        label = {
            Text(chipName)
        },
        selected = selected,
        trailingIcon = if (selected) {
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
    )
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
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,


            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = date)
                Text(text = "2024")
            }
            Column {
                Text(text = company)
                Text(text = category)
            }

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
                    Spacer(modifier = Modifier.padding(5.dp))
                }
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