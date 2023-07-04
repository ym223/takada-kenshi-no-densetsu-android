package com.example.takada_kenshi_no_densetsu_android.data.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayer
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class SoundPlayerModule {

    @ActivityScoped
    @Provides
    fun provideMediaPlayer(@ActivityContext context: Context) = ExoPlayer.Builder(context).build()
}

@Module
@InstallIn(ActivityComponent::class)
abstract class BindSoundPlayerModule {
    @ActivityScoped
    @Binds
    abstract fun soundPlayer(soundPlayerImpl: SoundPlayerImpl): SoundPlayer
}