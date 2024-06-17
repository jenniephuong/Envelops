package com.example.envelops.newTransaction

fun validateForm(field: String, emptyValue: String = "") : Int {
    var errorCode = 0
    if (field.isEmpty()) {
        errorCode += 1


    }
    return errorCode
}