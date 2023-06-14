package com.example.takada_kenshi_no_densetsu_android.data.di

import com.example.takada_kenshi_no_densetsu_android.data.Param.Companion.BASE_URL
import com.example.takada_kenshi_no_densetsu_android.data.service.densetsu.DensetsuApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory(
                    MediaType.get("application/json")
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun DensetsuApi(retrofit: Retrofit) = retrofit.create(DensetsuApi::class.java)
}
