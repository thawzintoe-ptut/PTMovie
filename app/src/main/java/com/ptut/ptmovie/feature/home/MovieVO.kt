package com.ptut.ptmovie.feature.home

import com.ptut.domain.mapper.UnidirectionalMap
import com.ptut.domain.model.MovieDomain
import org.threeten.bp.LocalDate
import javax.inject.Inject

data class MovieVO(
    val posterPath: String,
    val originalTitle: String,
    val releaseDate: LocalDate,
    val id: Long,
    val voteAverage: Float,
    val isFavorite: Boolean,
    val movieType: String
)

object MovieDomainToVOMapper {
    fun map(item: MovieDomain): MovieVO {
        return MovieVO(
            posterPath = item.posterPath,
            originalTitle = item.originalTitle,
            releaseDate = item.releaseDate,
            id = item.id,
            voteAverage = item.voteAverage,
            isFavorite = item.isFavorite,
            movieType = item.movieType
        )
    }
}
