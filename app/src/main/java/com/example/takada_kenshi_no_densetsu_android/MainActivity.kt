package com.example.takada_kenshi_no_densetsu_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.compose.TakadaKenshiAppTheme
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayer
import com.example.takada_kenshi_no_densetsu_android.ui.Home
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var soundPlayer: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakadaKenshiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(
                        playSound = soundPlayer::play,
                        stop = { soundPlayer.stop() }
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        soundPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPlayer.release()
    }
}