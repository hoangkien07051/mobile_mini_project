package com.vmo.mobileminiproject.model

data class DiriData(
    var nationalId: String,
    var fullName: String,
    var bankAccountNo: String,
    var education: String,
    var dateOfBirth: String,
) {
    fun isValidNationalId() : Boolean {
        return false
    }
}