package com.example.takada_kenshi_no_densetsu_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayer
import com.example.takada_kenshi_no_densetsu_android.ui.Home
import com.example.takada_kenshi_no_densetsu_android.ui.theme.TakadakenshinodensetsuandroidTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var player: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakadakenshinodensetsuandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(soundPlayer = player)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        player.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}