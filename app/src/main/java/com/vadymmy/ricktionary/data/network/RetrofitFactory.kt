package com.vadymmy.ricktionary.data.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface RetrofitFactory {
    fun createRetrofit(baseUrl: HttpUrl, okHttpClient: OkHttpClient) : Retrofit
}
