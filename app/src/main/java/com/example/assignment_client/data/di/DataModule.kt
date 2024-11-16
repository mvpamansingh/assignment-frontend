package com.example.assignment_client.data.di

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.assignment_client.data.remote.AppApis
import com.example.assignment_client.domain.repository.ApiRepository
import com.example.assignment_client.domain.repository.ApiRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
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


data class UserPreferences(
    val userId: String = ""
)
//
class UserPreferencesRepository(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    private object PreferencesKeys {
        val USER_ID = stringPreferencesKey("user_id")
    }

    val userPreferencesFlow: Flow<UserPreferences> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                userId = preferences[PreferencesKeys.USER_ID] ?: ""
            )
        }

    suspend fun updateUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_ID] = userId
        }
    }

    suspend fun clearUserId() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.USER_ID)
        }
    }
}


val dataModule = module {

    single{
        provideChatApi()
    }

    single{
        provideApiRepository(get(), context = androidContext())
    }


   single { UserPreferencesRepository(get()) }
}


