package com.example.takada_kenshi_no_densetsu_android.data.di

import com.example.takada_kenshi_no_densetsu_android.data.service.densetsu.DensetsuRepository
import com.example.takada_kenshi_no_densetsu_android.data.service.densetsu.DensetsuRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {

    @Singleton
    @Binds
    abstract fun densetsuRepository(densetsuRepositoryImpl: DensetsuRepositoryImpl): DensetsuRepository
}