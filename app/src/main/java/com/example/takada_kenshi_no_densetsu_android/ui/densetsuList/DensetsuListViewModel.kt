package com.example.takada_kenshi_no_densetsu_android.ui.densetsuList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.service.densetsu.DensetsuRepository
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DensetsuListViewModel @Inject constructor(
    val soundPlayer: SoundPlayer,
    val densetsuRepository: DensetsuRepository
) : ViewModel() {

    private var _densetsuList = MutableStateFlow<List<Densetsu>>(listOf())
    val densetsuList = _densetsuList.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(IO) {
                _densetsuList.value = densetsuRepository.getDensetsuList()
            }
        }
    }

    fun play(no: Int) {
        soundPlayer.play(no)
    }
}