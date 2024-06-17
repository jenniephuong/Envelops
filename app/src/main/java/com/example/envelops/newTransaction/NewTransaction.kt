package com.example.envelops.newTransaction

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.ui.theme.EnvelopsTheme
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionScreen(navController: NavController) {
    var payee by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var payeeError by remember { mutableStateOf("") }
    var isPayeeError by remember { mutableStateOf(false) }
    var amountError by remember { mutableStateOf("") }
    var isAmountError by remember { mutableStateOf(false) }
    var categoryError by remember { mutableStateOf("") }
    var isCategoryError by remember { mutableStateOf(false) }

    val selectedDate = remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var calendarState = rememberSheetState()

    val optionsArray = ArrayList<String>()

    CalendarDialog(
        state = calendarState, config = CalendarConfig(monthSelection = true, yearSelection = true),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate.value
        ) { newDate ->
            selectedDate.value = newDate
        },
    )
    EnvelopsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "New transaction", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.padding(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(modifier = Modifier.width(128.dp)) {
                    Text(
                        text = "Amount",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                    if (isAmountError) {
                        Text(
                            text = amountError,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed: Boolean by interactionSource.collectIsPressedAsState()
                Column {
                    Text(
                        text = "Date",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    selectedDate.value?.let {
                        OutlinedTextField(
                            value = it.format(formatter),
                            onValueChange = { amount = it },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            readOnly = true,
                            interactionSource = interactionSource

                        )
                    }
                    if (isPressed) {
                        calendarState.show()
                    }
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Payee",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = payee,
                isError = isPayeeError,
                onValueChange = { payee = it })
            if (isPayeeError) {
                Text(
                    text = payeeError,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Category",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )

                val optionsArray = ArrayList<String>()
                optionsArray.add("Rent")
                optionsArray.add("Travel")
                optionsArray.add("Food")
                optionsArray.add("Social")
                optionsArray.add("Shopping")
                val options = listOf("Travel", "Food", "Social", "Shopping")
                var selectedOptionText by remember { mutableStateOf("Select a category") }
                var isExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = !isExpanded },
                ) {
                    TextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        readOnly = true,
                        value = selectedOptionText,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false },
                    ) {
                        optionsArray.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    selectedOptionText = selectionOption
                                    category = selectionOption
                                    isExpanded = false
                                })
                        }
                    }

                }
            }
            if (isCategoryError) {
                Text(text = (categoryError))
            }
            var memo by remember { mutableStateOf("") }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Memo",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Optional") },
                value = memo,
                onValueChange = { memo = it })

            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(onClick = {
                }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)) {
                    Text(text = "Cancel")
                }


                Button(onClick = {
                    var errorCode: Boolean = false
                    isPayeeError = false
                    isAmountError = false
                    isCategoryError = false
                    if (payee.isEmpty()) {
                        isPayeeError = true
                        payeeError = "Enter a payee"
                        errorCode = true
                    }
                    if (amount.isEmpty()) {
                        isAmountError = true
                        amountError = "Enter an amount"
                        errorCode = true
                    }
                    if (category.isEmpty()) {
                        isCategoryError = true
                        categoryError = "Select a category"
                        errorCode = true
                    }

                    if (!errorCode) {
                        isPayeeError = true
                        payeeError = "TESTING"
                        isAmountError = false
                        optionsArray.add("TESTING ELEMENT")
                    }

                }) {
                    Text(text = "Add Transaction")
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun PreviewNewTransactionScreen() {
    NewTransactionScreen(navController = rememberNavController())
}