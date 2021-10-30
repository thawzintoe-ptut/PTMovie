package com.ptut.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieNetwork>,
    @Json(name = "total_pages")
    val total_pages: Int,
    @Json(name = "total_results")
    val total_results: Int
)