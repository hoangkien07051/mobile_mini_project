package com.vmo.mobileminiproject.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    private fun retrofitHelper(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ibnux.github.io")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun okHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }
    }

    private fun OkHttpClient.Builder.header() {
        addNetworkInterceptor {
            val builder = it.request().newBuilder()
            builder.addHeader("Accept", "*/*")
            it.proceed(builder.build())
        }
    }

    private fun getService(): Retrofit {
        val okHttpClient = okHttpClientBuilder().apply { header() }.build()
        return retrofitHelper(okHttpClient)
    }


    fun apiService(): NetworkService = getService().create(NetworkService::class.java)
}
