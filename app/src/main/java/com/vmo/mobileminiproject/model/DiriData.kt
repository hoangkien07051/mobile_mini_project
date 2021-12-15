package com.vmo.mobileminiproject.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class DiriData (
    var nationalId: String,
    var fullName: String,
    var bankAccountNo: String,
    var education: String,
    var dateOfBirth: String,
    var domicileAddress: String,
    var housingType: String,
    var no: String,
    var province: String,
) : Parcelable {
    constructor(): this("","","","","","","","","",)


}