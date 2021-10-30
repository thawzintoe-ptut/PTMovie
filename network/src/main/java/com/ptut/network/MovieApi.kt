package com.ptut.network

import com.ptut.network.entity.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/{type}")
    fun getMovie(
        @Path("type") type: String,
        @Query("api_key") api_key: String
    ): Call<MovieResponse>
}

