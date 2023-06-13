package com.example.takada_kenshi_no_densetsu_android.data.service

import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.db.DensetsuDao
import javax.inject.Inject

interface DensetsuRepository {
    suspend fun getDensetsu(): Densetsu
    suspend fun insertDensetsu(densetsu: Densetsu)
    suspend fun getLocalDensetsu(no: Int): Densetsu
    suspend fun getDensetsuAll(): List<Densetsu>
    suspend fun updateDensetsu(densetsu: Densetsu)
}

class DensetsuRepositoryImpl @Inject constructor(
    private val densetsuApi: DensetsuApi,
    private val densetsuDao: DensetsuDao
) : DensetsuRepository {

    override suspend fun getDensetsu() = densetsuApi.getDensetsu()

    override suspend fun insertDensetsu(densetsu: Densetsu) =
        densetsuDao.insertDensetsu(densetsu = densetsu)

    override suspend fun getLocalDensetsu(no: Int): Densetsu = densetsuDao.getLocalDensetsu(no = no)

    override suspend fun getDensetsuAll(): List<Densetsu> = densetsuDao.getDensetsuAll()
    override suspend fun updateDensetsu(densetsu: Densetsu) =
        densetsuDao.updateDensetsu(densetsu = densetsu)

}