package com.example.takada_kenshi_no_densetsu_android.ui.densetsu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.service.densetsu.DensetsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DensetsuViewModel @Inject constructor(
    private val densetsuRepository: DensetsuRepository,
) : ViewModel() {

    private var _densetsuState = MutableStateFlow<DensetsuState>(DensetsuState.Nothing)
    val densetsuState = _densetsuState.asStateFlow()

    private var _densetsuEvent = MutableStateFlow<List<DensetsuEvent>>(listOf())
    val densetsuEvent = _densetsuEvent.asStateFlow()

    fun getDensetsu() {
        viewModelScope.launch {
            runCatching {
                _densetsuState.value = DensetsuState.Loading
                delay(500)
                densetsuRepository.getDensetsu()
            }.onSuccess {
                withContext(IO) {
                    densetsuRepository.insertDensetsu(it)
                    _densetsuState.value =
                        DensetsuState.Success(densetsuRepository.getLocalDensetsu(it.no))
                }
            }.onFailure { it ->
                Log.d("error", it.toString())
                _densetsuState.value = DensetsuState.Error
                _densetsuEvent.update {
                    it + DensetsuEvent.Error
                }
            }
        }
    }

    fun updateDensetsu(densetsu: Densetsu) {
        viewModelScope.launch(IO) {
            densetsuRepository.updateDensetsu(densetsu.copy(isNew = false))
        }
    }

    fun consume(event: DensetsuEvent) {
        _densetsuEvent.update { e -> e.filterNot { it == event } }
    }
}

sealed class DensetsuState {
    object Nothing : DensetsuState()
    object Loading : DensetsuState()
    data class Success(val densetsu: Densetsu) : DensetsuState()
    object Error : DensetsuState()
}

sealed class DensetsuEvent {
    object Error : DensetsuEvent()
}