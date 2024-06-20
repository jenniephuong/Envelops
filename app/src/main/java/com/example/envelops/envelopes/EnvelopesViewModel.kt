package com.example.envelops.envelopes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.envelops.transactions.TransactionModel
import com.example.envelops.ui.theme.Green2
import java.time.LocalDate
import java.util.stream.Collectors
import kotlin.math.abs


class EnvelopesViewModel : ViewModel() {
//  Dummy variables since there is no DB implementation
    var envelopesArray by mutableStateOf(ArrayList<EnvelopeModel>())
    var transactionsArray by mutableStateOf(ArrayList<TransactionModel>())
//

    var envelope by mutableStateOf<EnvelopeModel?>(null)
        private set
    var colour by mutableStateOf<Color>(Color.Blue)
    var progress by mutableStateOf<Float>(0F)

    fun packageEnvelope(newEnvelope: EnvelopeModel) {
        envelope = newEnvelope
    }


    fun changeProgressColour(amount: Double, budgetedAmount: Double) {
        if (amount >= 0) { // positive progress bar
            progress = ((budgetedAmount - amount) / budgetedAmount).toFloat()
            colour = Green2
        } else { // negative progress bar
            progress = (abs(amount) / budgetedAmount).toFloat()
            colour = Color.Red
        }

    }

//  This function is okay, need to get transactions from DB, and add to the filtered transactions array based on envelopeName
    @RequiresApi(Build.VERSION_CODES.O)
    fun filterTransactions(envelopeName: String): ArrayList<TransactionModel> {
        var filteredTransactions: ArrayList<TransactionModel>
        filteredTransactions = transactionsArray.stream()
            .filter { transaction -> transaction.category.equals(envelopeName) }
            .collect(Collectors.toList()) as ArrayList<TransactionModel>

        return filteredTransactions
    }

//  This function should not be here, it should be in the DAO? - call the method in the repo class connected to DB
    fun addEnvelope(
        envelopeName: String,
        budgetedAmount: Double,
        amount: Double,
        type: String
    ) {
        var newEnvelope = EnvelopeModel("fff", envelopeName, budgetedAmount, amount, type)
        envelopesArray.add(newEnvelope)
    }

//  This function should not be there, it should call the getEvelopes function in a repo class to get DB data
    fun addMockData() {
        if (envelopesArray.size == 0) {
            envelopesArray.add(EnvelopeModel("1", "Transport", 500.00, -200.0, "Monthly"))
            envelopesArray.add(EnvelopeModel("1", "Food", 500.0, 20.0, "Monthly"))
            envelopesArray.add(EnvelopeModel("1", "Shopping", 50.0, 20.0, "Monthly"))
            envelopesArray.add(EnvelopeModel("1", "Rent", 50.0, -70.0, "Monthly"))
            envelopesArray.add(EnvelopeModel("1", "Social", 50.0, 20.0, "Monthly"))
            envelopesArray.add(EnvelopeModel("1", "Investments", 50.0, -2.0, "Monthly"))
            envelopesArray.add(EnvelopeModel("1", "Subscriptions", 50.0, 20.0, "Monthly"))
        }
    }

}