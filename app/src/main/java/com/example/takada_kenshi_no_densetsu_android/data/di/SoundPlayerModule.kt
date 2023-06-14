package com.example.takada_kenshi_no_densetsu_android.data.di

import android.content.Context
import android.media.MediaPlayer
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayer
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SoundPlayerModule {

    @Singleton
    @Provides
    fun provideMediaPlayer(@ApplicationContext context: Context) = ExoPlayer.Builder(context).build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindSoundPlayerModule {
    @Singleton
    @Binds
    abstract fun soundPlayer(soundPlayerImpl: SoundPlayerImpl): SoundPlayer
}