package com.example.envelops.transactions

import java.time.LocalDate
import java.time.temporal.TemporalAmount

data class TransactionModel(
    val transactionId: String,
    var date: LocalDate,
    var payee: String,
    var category: String,
    var amount: Double,
    var memo: String
)