package com.example.takada_kenshi_no_densetsu_android.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Densetsu(
    @SerialName("No")
    val no: Int,
    val text: String
)