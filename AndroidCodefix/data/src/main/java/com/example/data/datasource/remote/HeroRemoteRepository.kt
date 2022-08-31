package com.example.data.datasource.remote

import com.example.data.datasource.remote.model.HeroRemoteDo


interface HeroRemoteRepository {

    suspend fun getAllCharactersWithOffset(offset: Int, limit: Int): Result<HeroRemoteDo>
}