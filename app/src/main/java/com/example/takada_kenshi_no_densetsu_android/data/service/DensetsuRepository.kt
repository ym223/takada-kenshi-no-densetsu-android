package com.example.takada_kenshi_no_densetsu_android.data.service

import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import javax.inject.Inject

interface DensetsuRepository {
    suspend fun getDensetsu(): Densetsu
}

class DensetsuRepositoryImpl @Inject constructor(
    private val densetsuApi: DensetsuApi,
) : DensetsuRepository {

    override suspend fun getDensetsu() = densetsuApi.getDensetsu()

}