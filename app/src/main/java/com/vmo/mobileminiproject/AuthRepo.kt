package com.vmo.mobileminiproject

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepo @Inject constructor(private val networkService: NetworkService) {
    suspend fun getProvince(url: String) = withContext((Dispatchers.IO)) {
        networkService.getProvince(url)
    }
}