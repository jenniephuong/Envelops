package com.example.envelops.transactions

import java.time.LocalDate
import java.time.temporal.TemporalAmount

class TransactionModel(
    transactionId: String,
    date: LocalDate,
    payee: String,
    category: String,
    amount: Double,
    memo: String
) {
    var transactionId = transactionId
    var date = date
    var payee = payee
    var category = category
    var amount = amount
    var memo = memo

}