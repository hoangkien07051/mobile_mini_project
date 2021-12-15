package com.vmo.mobileminiproject.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.toDefaultDateString(): String {
    val sdf = SimpleDateFormat(Constants.dataTimeFormatShow)
    return sdf.format(this)
}