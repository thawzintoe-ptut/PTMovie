package com.ptut.domain.usecase

import com.ptut.domain.CoroutineUseCase
import com.ptut.domain.model.MovieDomain
import com.ptut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetFavoriteMovies @Inject constructor(
    private val movieRepository: MovieRepository
) : CoroutineUseCase<Unit, Flow<List<MovieDomain>>>(){
    override suspend fun provide(params: Unit): Flow<List<MovieDomain>> {
        return flowOf(movieRepository.getMovieFavorite())
    }
}