package com.example.envelops.envelopes

data class EnvelopeModel(
    val envelopeId: String,
    var envelopeName: String,
    var budgetedAmount: Double,
    var amount: Double,
    var type: String
)
