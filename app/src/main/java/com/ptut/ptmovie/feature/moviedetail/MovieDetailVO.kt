package com.ptut.ptmovie.feature.moviedetail

import com.ptut.domain.mapper.UnidirectionalMap
import com.ptut.domain.model.MovieDomain
import org.threeten.bp.LocalDate
import javax.inject.Inject

data class MovieDetailVO(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
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
    val movieType: String,
    val isFavorite: Boolean
)

class MovieDetailDomainToVOMapper @Inject constructor():UnidirectionalMap<MovieDomain,MovieDetailVO>{
    override fun map(item: MovieDomain): MovieDetailVO {
        return MovieDetailVO(
            posterPath = item.posterPath,
            originalTitle = item.originalTitle,
            releaseDate = item.releaseDate,
            id = item.id,
            voteAverage = item.voteAverage,
            adult = item.adult,
            backdropPath = item.backdropPath,
            genreIds = item.genreIds,
            originalLanguage = item.originalLanguage,
            overview = item.overview,
            popularity = item.popularity,
            title = item.title,
            video = item.video,
            voteCount = item.voteCount,
            isFavorite = item.isFavorite,
            movieType = item.movieType
        )
    }

}