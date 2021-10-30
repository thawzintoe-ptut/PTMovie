package com.ptut.domain.mapper

interface BidirectionalMap<F, T> {

  fun map(item: F): T

  fun reverseMap(item: T): F

}
