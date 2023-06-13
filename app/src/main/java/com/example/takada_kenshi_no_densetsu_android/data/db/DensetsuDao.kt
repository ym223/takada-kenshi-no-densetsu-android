package com.example.takada_kenshi_no_densetsu_android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu

@Dao
interface DensetsuDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDensetsu(densetsu: Densetsu)

    @Query("select * from densetsu_table where `no`=:no")
    fun getLocalDensetsu(no: Int): Densetsu

    @Query("select * from densetsu_table")
    fun getDensetsuAll(): List<Densetsu>

    @Update
    fun updateDensetsu(densetsu: Densetsu)
}