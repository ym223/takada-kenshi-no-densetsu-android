package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DensetsuScreen(
    densetsuViewModel: DensetsuViewModel = hiltViewModel()
) {

    val densetsuState = densetsuViewModel.densetsuState.collectAsState()

}

@Composable
fun DensetsuContent(
    densetsuState: DensetsuState,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when (densetsuState) {
                is DensetsuState.Success -> {
                    SuccessView(
                        densetsuState.densetsu.text
                    )
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
){
    Text(text = text)
}