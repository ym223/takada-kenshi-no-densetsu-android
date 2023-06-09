package com.example.takada_kenshi_no_densetsu_android.data.service

import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import retrofit2.http.GET
import retrofit2.http.Headers

interface DensetsuApi {
    @Headers("User-Agent: TakadaKenshi-no-Densetsu-Android")
    @GET(".")
    suspend fun getDensetsu(): Densetsu
}