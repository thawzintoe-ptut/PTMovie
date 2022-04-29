package com.ptut.data.entity

import com.ptut.data.mapper.UnidirectionalMap
import com.ptut.domain.model.MovieDomain
import org.threeten.bp.LocalDate
import javax.inject.Inject

data class MovieData(
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
    val movieType:String,
    val isFavorite: Boolean
)

class MovieDataToDomainMapper @Inject constructor():UnidirectionalMap<MovieData,MovieDomain>{
    override fun map(item: MovieData): MovieDomain {
        return MovieDomain(
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
