package usecase

import com.example.data.repository.MarvelManager
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mapper.toDomain
import model.Hero

class GetHeroByIdUseCase @Inject constructor(
    @Named("UseCase")
    private val dispatcher: CoroutineDispatcher,
    val marvelManager: MarvelManager
) {

    suspend operator fun invoke(id: Int): Result<Hero> = withContext(dispatcher) {
        marvelManager.getCharacterById(id).map { it.toDomain() }
    }
}