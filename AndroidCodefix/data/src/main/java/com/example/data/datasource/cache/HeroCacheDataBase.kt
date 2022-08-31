package com.example.data.datasource.cache

import com.example.data.datasource.remote.model.Results
import com.example.data.errors.Error
import javax.inject.Inject

class HeroCacheDataBase @Inject constructor(
    private val cache: MutableMap<Int, Results>
) {

    fun getHeroById(id: Int): Result<Results> {
        return cache[id]?.let { Result.success(it) } ?: Result.failure(Error.HeroNotFound())
    }

    fun saveHero(hero: Results) {
        cache[hero.id] = hero
    }

    fun clear() {
        cache.clear()
    }
}