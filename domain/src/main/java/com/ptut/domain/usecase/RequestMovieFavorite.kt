package com.ptut.domain.usecase

import com.ptut.domain.CoroutineUseCase
import com.ptut.domain.FlowCoroutineUseCase
import com.ptut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class RequestMovieFavorite @Inject constructor(
    private val movieRepository: MovieRepository
) : FlowCoroutineUseCase<RequestMovieFavorite.Params, Unit>() {

    data class Params(
        val isFavorite: Boolean,
        val movieId: Long,
        val movieType: String
    )

    override fun provide(params: Params):Flow<Unit> {
        movieRepository.setMovieFavorite(params.isFavorite, params.movieType, params.movieId)
        return emptyFlow()
    }
}