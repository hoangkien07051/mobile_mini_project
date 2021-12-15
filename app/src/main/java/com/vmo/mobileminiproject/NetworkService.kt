package com.vmo.mobileminiproject

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkService {
    @GET
    suspend fun getProvince(@Url url: String): Response<String>
}