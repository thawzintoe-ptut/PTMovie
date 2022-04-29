package com.ptut.data.mapper

interface UnidirectionalMap<F, T> {

  fun map(item: F): T
}

