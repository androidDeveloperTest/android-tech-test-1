package com.example.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.presentation.ui.model.HeroVo
import com.example.presentation.ui.model.toVo
import com.example.data.repository.MarvelManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mapper.toDomain
import model.Hero
import usecase.GetHeroesPagedUseCase

private const val PAGE_LIMIT = 20

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeroesPagedUseCase: GetHeroesPagedUseCase,
    val marvelRepository: MarvelManagerImpl
) : ViewModel() {

    val heroList: MutableLiveData<List<HeroVo>> = MutableLiveData(emptyList())
    val error: MutableLiveData<Throwable?> = MutableLiveData(null)
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private var page = 0


    suspend fun getHeroById(id: Int): Hero? {
        val results = marvelRepository.getCharacterById(id).getOrElse {
            error.value = it
            null
        }

        return results?.toDomain()
    }

    fun getHeroes() = viewModelScope.launch(Dispatchers.Main) {

        loading.value = true

        val results = getHeroesPagedUseCase(page, PAGE_LIMIT).getOrElse {
            error.value = it
            return@launch
        }

        val resultsToVo = results.results.map { it.toVo() }

        heroList.postValue(resultsToVo)

        loading.value = false
    }

    fun incrementPage() {
        page += PAGE_LIMIT
    }
}