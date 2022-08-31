package com.example.data.datasource.remote

import com.example.data.datasource.remote.model.HeroRemoteDo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("public/characters")
    suspend fun getAllCharactersWithOffset(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): HeroRemoteDo
}