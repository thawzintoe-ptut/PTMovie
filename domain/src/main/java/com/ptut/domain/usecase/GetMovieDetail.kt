package com.ptut.domain.usecase

import com.ptut.domain.CoroutineUseCase
import com.ptut.domain.model.MovieDomain
import com.ptut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMovieDetail @Inject constructor(
    private val movieRepository: MovieRepository
):CoroutineUseCase<Long,Flow<MovieDomain>>() {
    override suspend fun provide(params: Long): Flow<MovieDomain> {
        return flowOf(movieRepository.getMovieById(params))
    }
}