package com.lc.bangumidemo.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    val CONNECT_TIME_OUT = 10
    val READ_TIME_OUT = 10
    val WRITE_TIME_OUT = 10

    private const val BASE_URL = "http://api.pingcc.cn/"
    private var retrofit: Retrofit? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return retrofit!!
    }

    fun <T> create(service: Class<T>): T {
        return getRetrofit().create(service)
    }
}
