package com.ptut.domain.usecase

import com.ptut.domain.CoroutineUseCase
import com.ptut.domain.FlowCoroutineUseCase
import com.ptut.domain.model.MovieDomain
import com.ptut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetFavoriteMovies @Inject constructor(
    private val movieRepository: MovieRepository
) : FlowCoroutineUseCase<Unit, List<MovieDomain>>(){
    override fun provide(params: Unit): Flow<List<MovieDomain>> {
        return movieRepository.getMovieFavorite()
    }
}