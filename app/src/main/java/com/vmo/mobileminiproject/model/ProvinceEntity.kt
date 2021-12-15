package com.vmo.mobileminiproject.model

import com.google.gson.annotations.SerializedName

data class ProvinceEntity(
    @SerializedName("id")
    var id: String,
    @SerializedName("nama")
    var nama: String,
) {
}