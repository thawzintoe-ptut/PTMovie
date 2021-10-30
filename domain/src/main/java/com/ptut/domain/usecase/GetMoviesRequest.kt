package com.ptut.domain.usecase

import com.ptut.domain.CoroutineUseCase
import com.ptut.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesRequest @Inject constructor(
    private val movieRepository: MovieRepository
) :CoroutineUseCase<String,Unit>() {
    override suspend fun provide(params: String) {
        movieRepository.downloadMovies(params)
    }
}