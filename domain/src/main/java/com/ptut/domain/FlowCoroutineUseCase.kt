package com.ptut.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Created by Vincent on 2019-06-20
 */
abstract class FlowCoroutineUseCase<I, O> {

    suspend fun execute(param: I): Flow<O> {
        return provide(param)
            .flowOn(Dispatchers.IO)
    }

    protected abstract suspend fun provide(
        param: I
    ): Flow<O>

}