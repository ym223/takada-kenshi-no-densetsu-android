package com.example.takada_kenshi_no_densetsu_android.data.service.densetsu

import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.Param.MAX_DENSETSU
import com.example.takada_kenshi_no_densetsu_android.data.db.DensetsuDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DensetsuRepository {
    suspend fun getDensetsu(): Densetsu
    suspend fun insertDensetsu(densetsu: Densetsu)
    fun getLocalDensetsu(no: Int): Densetsu
    fun getDensetsuList(): Flow<List<Densetsu>>
    suspend fun updateDensetsu(densetsu: Densetsu)
}

class DensetsuRepositoryImpl @Inject constructor(
    private val densetsuApi: DensetsuApi,
    private val densetsuDao: DensetsuDao
) : DensetsuRepository {

    override suspend fun getDensetsu() = densetsuApi.getDensetsu()

    override suspend fun insertDensetsu(densetsu: Densetsu) =
        densetsuDao.insertDensetsu(densetsu = densetsu)

    override fun getLocalDensetsu(no: Int): Densetsu = densetsuDao.getLocalDensetsu(no = no)
    override suspend fun updateDensetsu(densetsu: Densetsu) =
        densetsuDao.updateDensetsu(densetsu = densetsu)

    override fun getDensetsuList(): Flow<List<Densetsu>> = flow {
        while (true){
            val densetsuList = createDensetsuLocalList()
            emit(densetsuList)
        }
    }

    fun createDensetsuLocalList(): List<Densetsu> {
        val densetsuAll = MutableList(MAX_DENSETSU) { Densetsu(no = -1, text = "", isNew = false) }
        for (i in 0 until MAX_DENSETSU) {
            densetsuAll[i].no = i
        }

        val densetsuLocalAll = getLocalDensetsuAll()

        for (i in densetsuLocalAll) {
            densetsuAll[i.no] = i
        }

        return densetsuAll
    }

    fun getLocalDensetsuAll(): List<Densetsu> = densetsuDao.getDensetsuAll()

}