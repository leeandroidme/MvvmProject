package com.newland.mvvmproject.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

object RetrofitService {
    private val logger = Logger.getLogger(RetrofitService::class.java.simpleName)
    private val BASE_URL = "https://www.wanandroid.com"
    val apiService: ApiService =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
            BASE_URL
        ).client(createOkClient()).build().create(ApiService::class.java)

    private fun createOkClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor {
            logger.info(it)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(5_000L, TimeUnit.MILLISECONDS)
            .readTimeout(10_000L,TimeUnit.MILLISECONDS)
            .writeTimeout(30_000,TimeUnit.MILLISECONDS)
            .build()
    }
}