package com.example.data.repository

import com.example.data.datasource.remote.ApiService
import com.example.data.datasource.remote.HeroRemoteRepositoryImpl
import com.example.data.datasource.remote.model.HeroRemoteDo
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MarvelManagerImplTest {

    lateinit var manager: MarvelManager

    @Before
    fun setUp(){
        manager = MarvelManagerImpl(
            remoteRepository = HeroRemoteRepositoryImpl(
                apiService = object : ApiService {
                    override suspend fun getAllCharactersWithOffset(offset: Int, limit: Int): HeroRemoteDo {
                        return mockk()
                    }

                }
            )
        )
    }

}