package com.ptut.domain.model

import com.ptut.domain.mapper.UnidirectionalMap
import org.threeten.bp.LocalDate
import javax.inject.Inject

data class MovieDomain(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Long>,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: LocalDate,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int,
    val isFavorite:Boolean,
    val movieType:String
)
