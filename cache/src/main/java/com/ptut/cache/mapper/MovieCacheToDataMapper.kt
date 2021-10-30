package com.ptut.cache.mapper

import com.ptut.cache.database.Movie
import com.ptut.data.entity.MovieData
import com.ptut.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class MovieCacheToDataMapper @Inject constructor():UnidirectionalMap<Movie,MovieData> {
    override fun map(item: Movie): MovieData {
        return MovieData(
            posterPath = item.posterPath,
            originalTitle = item.originalTitle,
            releaseDate = item.releaseDate,
            id = item.movieId,
            voteAverage = item.voteAverage,
            adult = item.adult?:false,
            backdropPath = item.backDropPath,
            genreIds = item.genreIds,
            originalLanguage = item.originalLanguage,
            overview = item.overview,
            popularity = item.popularity,
            title = item.movieTitle,
            video = item.video?:false,
            voteCount = item.voteCount.toInt(),
            movieType = item.movieType!!.type,
            isFavorite = item.isFavorite
        )
    }
}