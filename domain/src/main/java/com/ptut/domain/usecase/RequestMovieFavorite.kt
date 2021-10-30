package com.ptut.domain.usecase

import com.ptut.domain.CoroutineUseCase
import com.ptut.domain.repository.MovieRepository
import javax.inject.Inject

class RequestMovieFavorite @Inject constructor(
    private val movieRepository: MovieRepository
) : CoroutineUseCase<RequestMovieFavorite.Params, Unit>() {

    data class Params(
        val isFavorite: Boolean,
        val movieId: Long,
        val movieType: String
    )

    override suspend fun provide(params: Params) {
        movieRepository.setMovieFavorite(params.isFavorite, params.movieType, params.movieId)
    }
}