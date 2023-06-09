package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DensetsuScreen(
    densetsuViewModel: DensetsuViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit){
        densetsuViewModel.getDensetsu()
    }

    Text("DensetsuScreen")
}