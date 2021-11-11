package com.ptut.domain.usecase

import com.ptut.domain.FlowCoroutineUseCase
import com.ptut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetMoviesRequest @Inject constructor(
    private val movieRepository: MovieRepository
) :FlowCoroutineUseCase<String,Unit>() {
    override fun provide(params: String):Flow<Unit> {
        movieRepository.downloadMovies(params)
        return emptyFlow()
    }
}