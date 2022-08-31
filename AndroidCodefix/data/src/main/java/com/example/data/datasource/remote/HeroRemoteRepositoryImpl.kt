package com.example.data.datasource.remote

import com.example.data.datasource.remote.model.HeroRemoteDo
import javax.inject.Inject

class HeroRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HeroRemoteRepository {

    override suspend fun getAllCharactersWithOffset(offset: Int, limit: Int): Result<HeroRemoteDo> {
        return try {
            val result = apiService.getAllCharactersWithOffset(offset, limit)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}