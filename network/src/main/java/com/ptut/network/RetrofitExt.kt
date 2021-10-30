package com.ptut.network

import com.ptut.network.exception.NetworkException
import retrofit2.Call

/**
 * Created by Vincent on 2019-09-10
 */
fun <T> Call<T>.executeOrThrow(): T {

  val response = this.execute()

  if (response.isSuccessful.not()) {
    throw NetworkException(response.errorBody(), response.code())
  }

  val body = response.body() ?: throw NetworkException()

  return body
}

