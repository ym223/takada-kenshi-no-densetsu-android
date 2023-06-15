package com.example.takada_kenshi_no_densetsu_android.data.service.densetsu

import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.db.DensetsuDao
import javax.inject.Inject

interface DensetsuRepository {
    suspend fun getDensetsu(): Densetsu
    suspend fun insertDensetsu(densetsu: Densetsu)
    fun getLocalDensetsu(no: Int): Densetsu
    fun getDensetsuList(): List<Densetsu>
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

    override fun getDensetsuList(): List<Densetsu> {
        val densetsuAll = MutableList(232) { Densetsu(no = -1, text = "", isNew = false) }
        for (i in 0..231) {
            densetsuAll[i].no = i
        }

        val densetsuLocalAll = getLocalDensetsuAll()

        for(i in densetsuLocalAll){
            densetsuAll[i.no] = i
        }

        return densetsuAll
    }

    fun getLocalDensetsuAll(): List<Densetsu> = densetsuDao.getDensetsuAll()

}