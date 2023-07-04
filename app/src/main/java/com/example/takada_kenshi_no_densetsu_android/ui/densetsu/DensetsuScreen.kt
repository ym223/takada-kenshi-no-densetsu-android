package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.takada_kenshi_no_densetsu_android.R
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu

@Composable
fun DensetsuScreen(
    densetsuViewModel: DensetsuViewModel = hiltViewModel(),
    playSound: (Int) -> Unit
) {
    val densetsuState by densetsuViewModel.densetsuState.collectAsState()

    DensetsuContent(
        densetsuState = densetsuState,
        onClick = { densetsuViewModel.getDensetsu() },
        update = densetsuViewModel::updateDensetsu,
        playSound = playSound
    )
}

@Composable
fun DensetsuContent(
    densetsuState: DensetsuState,
    onClick: () -> Unit,
    update: (Densetsu) -> Unit,
    playSound: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.size(320.dp)){
            Image(
                painter = painterResource(id = R.drawable.img_takada),
                contentDescription = "Takada_face",
                contentScale = ContentScale.Fit
            )
        }
        when (densetsuState) {
            is DensetsuState.Success -> {
                SuccessView(
                    isNew = densetsuState.densetsu.isNew,
                    no = densetsuState.densetsu.no,
                    text = densetsuState.densetsu.text,
                    onClick = {
                        playSound(densetsuState.densetsu.no)
                    }
                )
                update(densetsuState.densetsu)
                Spacer(modifier = Modifier.height(16.dp))
            }

            DensetsuState.Loading -> {
                LoadingView()
            }

            else -> {

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClick) {
            Text(text = "伝説を探す")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SuccessView(
    isNew: Boolean,
    no: Int,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .defaultMinSize(minHeight = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if ((no > 0) && (no < 81)) {
                    IconButton(onClick = onClick) {
                        Icon(imageVector = Icons.Filled.VolumeUp, contentDescription = "read aloud")
                    }
                } else {
                    Spacer(modifier = Modifier.size(0.dp))
                }

                if (isNew) {
                    Text(text = "New!!", color = Color.Red)
                }
            }
            Text(text = text)
        }
    }
}

@Composable
fun LoadingView() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 56.dp, vertical = 56.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}