package com.example.envelops.transactions

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.temporal.ChronoField
import java.util.stream.Collectors


class TransactionsViewModel : ViewModel() {
    var transactionsArray by mutableStateOf(ArrayList<TransactionModel>())
    var selectedChip by mutableStateOf<String?>(null)

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun addTransaction(
        date: LocalDate,
        payee: String,
        category: String,
        amount: Double,
        memo: String
    ) {
        var newTransaction = TransactionModel(getRandomString(6),date, payee, category, amount, memo)
        transactionsArray.add(newTransaction)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filterTransactions(): ArrayList<TransactionModel> {
        var filteredTransactions: ArrayList<TransactionModel>
        var currentDate = LocalDate.now()
        when (selectedChip) {
            "Day" -> {
                filteredTransactions = transactionsArray.stream()
                    .filter { transaction -> transaction.date.equals(currentDate) }
                    .collect(Collectors.toList()) as ArrayList<TransactionModel>
            }

            "Week" -> {
                var currentWeek = currentDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR)
                var currentYear = currentDate.year
                filteredTransactions = transactionsArray.stream()
                    .filter { transaction ->
                        transaction.date.get(ChronoField.ALIGNED_WEEK_OF_YEAR) === currentWeek &&
                                transaction.date.year === currentYear
                    }
                    .collect(Collectors.toList()) as ArrayList<TransactionModel>
            }

            "Month" -> {
                val currentMonth = currentDate.month
                filteredTransactions = transactionsArray.stream()
                    .filter { transaction -> transaction.date.month === currentMonth }
                    .collect(Collectors.toList()) as ArrayList<TransactionModel>
            }

            "Year" -> {
                val currentYear = currentDate.year
                filteredTransactions = transactionsArray.stream()
                    .filter { transaction -> transaction.date.year === currentYear }
                    .collect(Collectors.toList()) as ArrayList<TransactionModel>
            }

            else -> {
                filteredTransactions = transactionsArray
            }
        }

        return filteredTransactions
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addMockData() {
        addTransaction(LocalDate.now(), "Payee1", "Category1", -100.00, "")
        addTransaction(LocalDate.now(), "Payee1", "Category1", 100.00, "Memo1")
        addTransaction(LocalDate.now(), "Payee1", "Category1", 100.00, "Memo1")
        addTransaction(LocalDate.of(2023, 6, 16), "Payee2", "Category2", 200.00, "Memo2")
        addTransaction(LocalDate.of(2023, 6, 16), "Payee2", "Category2", 200.00, "Memo2")
        addTransaction(LocalDate.of(2023, 6, 16), "Payee2", "Category2", 200.00, "Memo2")
        addTransaction(LocalDate.of(2023, 6, 16), "Payee2", "Category2", 200.00, "Memo2")
        addTransaction(LocalDate.now().plusDays(1), "Payee3", "Category3", 300.00, "Memo3")
        addTransaction(LocalDate.now().plusDays(2), "Payee3", "Category3", 300.00, "Memo3")
        addTransaction(LocalDate.now().plusDays(3), "Payee3", "Category3", 300.00, "Memo3")
        addTransaction(LocalDate.now().plusDays(7), "Payee3", "Category3", 300.00, "Memo3")
        addTransaction(
            LocalDate.now().withDayOfMonth(1),
            "Payee4",
            "Category4",
            400.00,
            "Memo4"
        )
        addTransaction(
            LocalDate.now().withDayOfMonth(1),
            "Payee4",
            "Category4",
            400.00,
            "Memo4"
        )
        addTransaction(
            LocalDate.now().withDayOfMonth(1),
            "Payee4",
            "Category4",
            400.00,
            "Memo4"
        )
        addTransaction(
            LocalDate.now().withDayOfMonth(1),
            "Payee4",
            "Category4",
            400.00,
            "Memo4"
        )
        addTransaction(LocalDate.now().minusMonths(1), "Payee5", "Category5", 500.00, "Memo5")
        addTransaction(LocalDate.now().minusMonths(1), "Payee5", "Category5", 500.00, "Memo5")
        addTransaction(LocalDate.now().minusMonths(1), "Payee5", "Category5", 500.00, "Memo5")
        addTransaction(LocalDate.now().minusMonths(1), "Payee5", "Category5", 500.00, "Memo5")
    }
}