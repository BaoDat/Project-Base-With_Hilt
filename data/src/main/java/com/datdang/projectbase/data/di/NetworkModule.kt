package com.datdang.projectbase.data.di

import android.content.Context
import com.datdang.projectbase.data.BuildConfig
import com.datdang.projectbase.data.interceptor.HeaderInterceptor
import com.datdang.projectbase.data.interceptor.MockInterceptor
import com.datdang.projectbase.data.service.AppServerService
import com.datdang.projectbase.data.session.LoginProfile
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    fun provideMockInterceptor(@ApplicationContext context: Context): MockInterceptor =
        MockInterceptor(context)

    @Provides
    fun provideHeaderInterceptor(loginProfile: LoginProfile): HeaderInterceptor =
        HeaderInterceptor(loginProfile)

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        mockInterceptor: MockInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(headerInterceptor)
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        if (BuildConfig.FLAVOR.equals("mockIntercept", true)) {
            httpClient.addInterceptor(mockInterceptor)
        }
        return httpClient.build()
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideBaseUrl(): String = BuildConfig.SERVER_BASE_URL

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    fun provideRxJava3AdapterFactory(): RxJava3CallAdapterFactory =
        RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAppServerService(retrofit: Retrofit): AppServerService =
        retrofit.create(AppServerService::class.java)
}
