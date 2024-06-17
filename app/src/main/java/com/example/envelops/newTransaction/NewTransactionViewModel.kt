package com.example.envelops.newTransaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class NewTransactionViewModel : ViewModel() {
    var payee by mutableStateOf("")
        private set
    var payeeError by mutableStateOf("")
        private set
    var isPayeeError by mutableStateOf(false)
        private set
    var amount by mutableStateOf("")
        private set
    var amountError by mutableStateOf("")
        private set
    var isAmountError by mutableStateOf(false)
        private set
    var category by mutableStateOf("Select a category")
        private set
    var categoryError by mutableStateOf("")
        private set
    var isCategoryError by mutableStateOf(false)
        private set
    var selectedDate by mutableStateOf<LocalDate?>(LocalDate.now())
        private set
    var memo by mutableStateOf("")
        private set

    var categoryArray by mutableStateOf(ArrayList<String>())
        private set

    fun validateForm(): Boolean {
        var errorCode = false
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
        if (category == "Select a category") {
            isCategoryError = true
            categoryError = "Select a category"
            errorCode = true
        }
        return errorCode
    }

    fun updatePayee(newPayee: String) {
        payee = newPayee
    }

    fun updateAmount(newAmount: String) {
        amount = newAmount
    }

    fun updateCategory(newCategory: String) {
        category = newCategory
    }

    fun updateSelectedDate(newDate: LocalDate) {
        selectedDate = newDate
    }

    fun updateMemo(newMemo: String) {
        memo = newMemo
    }

    fun addCategory(category: String) {
        categoryArray.add(category)
    }

}