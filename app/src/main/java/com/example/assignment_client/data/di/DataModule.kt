package com.example.assignment_client.data.di

import android.content.Context
import com.example.assignment_client.data.remote.AppApis
import com.example.assignment_client.domain.repository.ApiRepository
import com.example.assignment_client.domain.repository.ApiRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideChatApi(): AppApis{

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .baseUrl("http://192.168.1.4:3000")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AppApis::class.java)
}


fun provideApiRepository(chatApi: AppApis, context: Context): ApiRepository {
    return ApiRepositoryImpl(chatApi,context )
}





val dataModule = module {

    single{
        provideChatApi()
    }

    single{
        provideApiRepository(get(), context = androidContext())
    }
}


