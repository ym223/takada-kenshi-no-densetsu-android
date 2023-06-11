package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.service.DensetsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DensetsuViewModel @Inject constructor(
        private val densetsuRepository: DensetsuRepository
) : ViewModel() {

    private var _densetsuState = MutableStateFlow<DensetsuState>(DensetsuState.Nothing)

    val densetsuState = _densetsuState.asStateFlow()

    fun getDensetsu() {
        viewModelScope.launch {
            runCatching {
                _densetsuState.value = DensetsuState.Loading
                densetsuRepository.getDensetsu()
            }.onSuccess {
                _densetsuState.value = DensetsuState.Success(it)
            }.onFailure { it ->
                Log.d("error", it.toString())
                _densetsuState.value = DensetsuState.Error
            }
        }
    }
}

sealed class DensetsuState {
    object Nothing: DensetsuState()
    object Loading: DensetsuState()
    data class Success(val densetsu: Densetsu): DensetsuState()
    object Error: DensetsuState()
}