package com.ptut.domain.mapper

interface UnidirectionalMap<F, T> {

  fun map(item: F): T
}

