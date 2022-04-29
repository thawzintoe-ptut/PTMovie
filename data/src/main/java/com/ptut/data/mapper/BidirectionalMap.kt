package com.ptut.data.mapper

interface BidirectionalMap<F, T> {

  fun map(item: F): T

  fun reverseMap(item: T): F

}
