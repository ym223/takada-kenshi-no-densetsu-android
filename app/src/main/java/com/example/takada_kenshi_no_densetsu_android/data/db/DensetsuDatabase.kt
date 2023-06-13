package com.example.takada_kenshi_no_densetsu_android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu

@Database(entities = [Densetsu::class], version = 1, exportSchema = false)
abstract class DensetsuDatabase : RoomDatabase() {
    abstract fun densetsuDao(): DensetsuDao

}