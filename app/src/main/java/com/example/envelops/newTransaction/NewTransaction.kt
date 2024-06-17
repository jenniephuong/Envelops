package com.example.envelops.newTransaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envelops.navigation.Screens
import com.example.envelops.ui.theme.EnvelopsTheme
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionScreen(navController: NavController) {
    val viewModel = viewModel<NewTransactionViewModel>()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState, config = CalendarConfig(monthSelection = true, yearSelection = true),
        selection = CalendarSelection.Date(
            selectedDate = viewModel.selectedDate
        ) { newDate ->
            viewModel.updateSelectedDate(newDate)
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
                        value = viewModel.amount,
                        onValueChange = { viewModel.updateAmount(it) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                    if (viewModel.isAmountError) {
                        Text(
                            text = viewModel.amountError,
                            color = MaterialTheme.colorScheme.error,
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
                    viewModel.selectedDate?.let {
                        OutlinedTextField(
                            value = it.format(formatter),
                            onValueChange = { viewModel.updateAmount(it) },
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
                value = viewModel.payee,
                isError = viewModel.isPayeeError,
                onValueChange = { viewModel.updatePayee(it) })
            if (viewModel.isPayeeError) {
                Text(
                    text = viewModel.payeeError,
                    color = MaterialTheme.colorScheme.error,
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

                viewModel.addCategory("Rent")
                viewModel.addCategory("Travel")
                viewModel.addCategory("Food")
                viewModel.addCategory("Social")
                viewModel.addCategory("Shopping")
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
                        value = viewModel.category,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false },
                    ) {
                        viewModel.categoryArray.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    viewModel.updateCategory(selectionOption)
                                    isExpanded = false
                                })
                        }
                    }
                }
            }
            if (viewModel.isCategoryError) {
                Text(text = (viewModel.categoryError))
            }

            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Memo",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Optional") },
                value = viewModel.memo,
                onValueChange = { viewModel.updateMemo(it) })

            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(onClick = {
                    navController.navigate(Screens.Transactions.screen)
                }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)) {
                    Text(text = "Cancel")
                }

                Button(onClick = {
                    val errorCode = viewModel.validateForm()
                    if (!errorCode) {
                        navController.navigate(Screens.Envelopes.screen)
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