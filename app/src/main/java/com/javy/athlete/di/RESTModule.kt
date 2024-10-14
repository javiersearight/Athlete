package com.javy.athlete.di

import com.javy.athlete.BuildConfig
import com.javy.athlete.data.source.remote.rest.OauthInterceptor
import com.javy.athlete.data.source.remote.rest.service.AthleteApiService
import com.javy.athlete.data.source.remote.rest.service.OauthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RESTModule {

    @Singleton
    @Provides
    fun provideOauthInterceptor(): OauthInterceptor {
        return OauthInterceptor()
    }

    @Singleton
    @Provides
    fun provideHttpClient(oauthInterceptor: OauthInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(oauthInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun athleteApiService(retrofit: Retrofit): AthleteApiService =
        retrofit.create(AthleteApiService::class.java)


    @Singleton
    @Provides
    fun oauthApiService(retrofit: Retrofit): OauthApiService =
        retrofit.create(OauthApiService::class.java)
}