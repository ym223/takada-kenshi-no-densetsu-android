package com.example.takada_kenshi_no_densetsu_android.ui.densetsuList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.service.densetsu.DensetsuRepository
import com.example.takada_kenshi_no_densetsu_android.data.service.sound.SoundPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
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

    suspend fun getDensetsuList() {
        val densetsuListAll = MutableList(232) { Densetsu(no = -1, text = "", isNew = false) }

        for (i in 0..231) {
            densetsuListAll[i].no = i
        }

        viewModelScope.launch {
            withContext(IO) {
                _densetsuList.value = densetsuRepository.getDensetsuAll()
            }
        }.join()

        _densetsuList.value.let {
            for (densetsu in it) {
                densetsuListAll[densetsu.no] = densetsu
            }
        }
        _densetsuList.value = densetsuListAll
    }

    fun play(no: Int) {
        soundPlayer.play(no)
    }
}