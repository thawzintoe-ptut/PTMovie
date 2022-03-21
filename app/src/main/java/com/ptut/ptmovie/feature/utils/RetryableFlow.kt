package com.ptut.ptmovie.feature.utils

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.* // ktlint-disable no-wildcard-imports

@FlowPreview
fun <T> retryAbleFlow(retryTrigger: RetryTrigger, flowProvider: () -> Flow<T>) =
    retryTrigger.retryEvent.filter { it == RetryTrigger.State.RETRYING }
        .flatMapConcat { flowProvider() }
        .onEach { retryTrigger.retryEvent.value = RetryTrigger.State.IDLE }

class RetryTrigger {
    enum class State { RETRYING, IDLE }
    val retryEvent = MutableStateFlow(State.RETRYING)
    fun retry() {
        retryEvent.value = State.RETRYING
    }
}
