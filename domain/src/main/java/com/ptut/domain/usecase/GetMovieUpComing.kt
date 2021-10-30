package com.ptut.domain.usecase

import com.ptut.domain.CoroutineUseCase
import com.ptut.domain.FlowCoroutineUseCase
import com.ptut.domain.model.MovieDomain
import com.ptut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMovieUpComing @Inject constructor(
    private val movieRepository: MovieRepository
) : CoroutineUseCase<String,Flow<List<MovieDomain>>>(){
    override suspend fun provide(params: String): Flow<List<MovieDomain>> {
        return flowOf(movieRepository.getMovieListByType(params))
    }
}