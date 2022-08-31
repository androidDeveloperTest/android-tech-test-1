package usecase

import com.example.data.repository.MarvelManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import model.HeroPaging
import javax.inject.Inject
import javax.inject.Named
import mapper.toDomain

class GetHeroesPagedUseCase @Inject constructor(
    @Named("UseCase")
    private val dispatcher: CoroutineDispatcher,
    private val marvelManager: MarvelManager
) {

    suspend operator fun invoke(page: Int, limit: Int): Result<HeroPaging> = withContext(dispatcher) {
        marvelManager.getAllCharactersPaged(offset = page, limit = limit).map {
            it.toDomain()
        }
    }
}