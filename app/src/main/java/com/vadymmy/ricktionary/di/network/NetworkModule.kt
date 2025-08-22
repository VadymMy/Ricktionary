package com.vadymmy.ricktionary.di.network

import com.vadymmy.ricktionary.BuildConfig
import com.vadymmy.ricktionary.data.characters.remote.api.CharactersApi
import com.vadymmy.ricktionary.data.network.RetrofitFactory
import com.vadymmy.ricktionary.data.network.RetrofitFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

private const val CHARACTERS_API_BASE_URL = "https://rickandmortyapi.com/api"
private const val CHARACTERS_API_BASE_URL_NAME = "characters_api_base_url_name"

private const val CHARACTERS_RETROFIT = "characters_retrofit"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    @Named(CHARACTERS_API_BASE_URL_NAME)
    fun provideCharactersApiBaseUrl(): HttpUrl = CHARACTERS_API_BASE_URL.toHttpUrl()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    fun provideNoAuthOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitFactory(retrofitFactoryImpl: RetrofitFactoryImpl): RetrofitFactory = retrofitFactoryImpl

    @Provides
    @Singleton
    @Named(CHARACTERS_RETROFIT)
    fun provideCharactersRetrofit(
        retrofitFactory: RetrofitFactory,
        @Named(CHARACTERS_API_BASE_URL_NAME)
        charactersApiBaseUrl: HttpUrl,
        okHttpClient: OkHttpClient
    ): Retrofit = retrofitFactory.createRetrofit(
        baseUrl = charactersApiBaseUrl,
        okHttpClient = okHttpClient
    )

    @Provides
    @Singleton
    fun provideCharactersApi(
        @Named(CHARACTERS_RETROFIT)
        retrofit: Retrofit
    ): CharactersApi = retrofit.create(CharactersApi::class.java)
}
