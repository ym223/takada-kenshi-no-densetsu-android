package com.example.takada_kenshi_no_densetsu_android.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "densetsu_table")
data class Densetsu(
    @SerialName("No")
    @PrimaryKey var no: Int,
    val text: String,
    val isNew: Boolean = true
)