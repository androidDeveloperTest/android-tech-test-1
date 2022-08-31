package usecase

import com.example.data.datasource.remote.ApiService
import com.example.data.datasource.remote.HeroRemoteRepositoryImpl
import com.example.data.datasource.remote.model.Data
import com.example.data.datasource.remote.model.HeroRemoteDo
import com.example.data.datasource.remote.model.Results
import com.example.data.datasource.remote.model.Thumbnail
import com.example.data.errors.Error
import com.example.data.repository.MarvelManagerImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetHeroByIdUseCaseTest {

    val apiService = mockk<ApiService>()

    var useCase: GetHeroByIdUseCase? = null

    @Before
    fun setUp() {

        useCase = GetHeroByIdUseCase(
            dispatcher = Dispatchers.Unconfined,
            marvelManager = MarvelManagerImpl(
                remoteRepository = HeroRemoteRepositoryImpl(
                    apiService = apiService
                )
            )
        )
    }


    @Test
    fun `success OK`() = runBlocking {

        coEvery { apiService.getAllCharactersWithOffset(0, 0) }.returns(mockk<HeroRemoteDo>().also {
            coEvery { it.data }.returns(
                Data(
                    0,
                    1,
                    1,
                    1,
                    listOf(
                        Results(
                            id = 0,
                            name = "Pepe",
                            description = "",
                            modified = "",
                            resourceURI = "",
                            urls = emptyList(),
                            thumbnail = Thumbnail("", ""),
                            comics = mockk(),
                            stories = mockk(),
                            events = mockk(),
                            series = mockk()
                        )
                    )
                )
            )
        })

        useCase!!.marvelManager.getAllCharactersPaged(0, 0)

        val result = useCase!!.invoke(123)

        assertEquals("Pepe", result.getOrNull()!!.name)
    }


    @Test
    fun `failure`() = runBlocking {

        val result = useCase?.invoke(123)

        assertTrue(result!!.isFailure)
        assertTrue(result.exceptionOrNull() is Error.HeroNotFound)
    }

}