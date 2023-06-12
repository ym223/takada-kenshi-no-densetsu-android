package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.takada_kenshi_no_densetsu_android.R

@Composable
fun DensetsuScreen(
    densetsuViewModel: DensetsuViewModel = hiltViewModel()
) {
    val densetsuState by densetsuViewModel.densetsuState.collectAsState()

    DensetsuContent(densetsuState = densetsuState) {
        densetsuViewModel.getDensetsu()
    }
}

@Composable
fun DensetsuContent(
    densetsuState: DensetsuState,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
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
                        densetsuState.densetsu.text
                    )
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
        }
    }
}

@Composable
fun SuccessView(
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(text = text)
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