package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takada_kenshi_no_densetsu_android.data.service.DensetsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DensetsuViewModel @Inject constructor(
    private val densetsuRepository: DensetsuRepository
) : ViewModel() {

    fun getDensetsu() {
        viewModelScope.launch {
            Log.d("densetsu",densetsuRepository.getDensetsu().toString())
        }
    }
}