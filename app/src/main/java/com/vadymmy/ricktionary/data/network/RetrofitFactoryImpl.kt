package com.vadymmy.ricktionary.data.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitFactoryImpl @Inject constructor(
    private val converterFactory: GsonConverterFactory
) : RetrofitFactory {

    override fun createRetrofit(baseUrl: HttpUrl, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(converterFactory).build()
    }
}
