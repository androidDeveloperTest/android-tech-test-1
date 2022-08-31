package com.example.data.repository

import com.example.data.datasource.cache.HeroCacheDataBase
import com.example.data.datasource.remote.HeroRemoteRepository
import com.example.data.datasource.remote.model.Data
import javax.inject.Inject
import com.example.data.datasource.remote.model.Results


interface MarvelManager {
    suspend fun getAllCharactersPaged(offset: Int, limit: Int): Result<Data>
    suspend fun getCharacterById(characterId: Int): Result<Results>
    suspend fun deleteCache()
}


class MarvelManagerImpl @Inject constructor(
    private val remoteRepository: HeroRemoteRepository,
) : MarvelManager {

    private val cacheRepository = HeroCacheDataBase(cache = mutableMapOf())

    override suspend fun getAllCharactersPaged(offset: Int, limit: Int): Result<Data> {
        val result = remoteRepository.getAllCharactersWithOffset(offset, limit).getOrElse {
            return Result.failure(it)
        }.also { heroPaging ->
            heroPaging.data.results.forEach {
                cacheRepository.saveHero(it)
            }
        }

        return Result.success(result.data)
    }

    override suspend fun getCharacterById(characterId: Int): Result<Results> {
        return cacheRepository.getHeroById(characterId)
    }

    override suspend fun deleteCache() {
        cacheRepository.clear()
    }
}