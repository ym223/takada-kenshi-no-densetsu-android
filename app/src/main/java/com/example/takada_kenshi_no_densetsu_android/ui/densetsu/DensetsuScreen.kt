package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.takada_kenshi_no_densetsu_android.R
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu

@Composable
fun DensetsuScreen(
    densetsuViewModel: DensetsuViewModel = hiltViewModel()
) {
    val densetsuState by densetsuViewModel.densetsuState.collectAsState()
    val scrollState = rememberScrollState()

    DensetsuContent(
        densetsuState = densetsuState,
        scrollState = scrollState,
        onClick = { densetsuViewModel.getDensetsu() },
        update = densetsuViewModel::updateDensetsu,
        playSound = densetsuViewModel::playDensetsu
    )
}

@Composable
fun DensetsuContent(
    densetsuState: DensetsuState,
    scrollState: ScrollState,
    onClick: () -> Unit,
    update: (Densetsu) -> Unit,
    playSound: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_takada),
                contentDescription = "Takada_face",
                modifier = Modifier.scale(0.8f)
            )
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
            Button(onClick = onClick) {
                Text(text = "伝説を探す")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
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