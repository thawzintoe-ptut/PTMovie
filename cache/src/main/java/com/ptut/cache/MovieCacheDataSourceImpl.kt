package com.ptut.cache

import com.ptut.cache.database.AppDatabase
import com.ptut.cache.mapper.MovieCacheToDataMapper
import com.ptut.data.dataSource.MovieCacheDataSource
import com.ptut.data.entity.MovieData
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieCacheDataSourceImpl @Inject constructor(
    private val db: AppDatabase,
    private val movieCacheMapper: MovieCacheToDataMapper
) : MovieCacheDataSource {
    override fun putMovies(movies: List<MovieData>) {
        db.transaction {
            movies.forEach { movie ->
                db.movieQueries.insertMovie(
                    posterPath = movie.posterPath,
                    originalTitle = movie.originalTitle,
                    releaseDate = movie.releaseDate,
                    movieId = movie.id,
                    voteAverage = movie.voteAverage,
                    adult = movie.adult,
                    backDropPath = movie.backdropPath,
                    genreIds = movie.genreIds,
                    originalLanguage = movie.originalLanguage,
                    overview = movie.overview,
                    popularity = movie.popularity,
                    movieTitle = movie.title,
                    video = movie.video,
                    voteCount = movie.voteCount.toLong(),
                    movieType = movie.movieType.toMovieType(),
                    isFavorite = false
                )
            }
        }
    }

    override fun getMoviesByType(movieType: String): Flow<List<MovieData>> {
        return db.movieQueries.selectAllMoviesByType(movieType.toMovieType())
            .asFlow()
            .mapToList()
            .map { movies -> movies.map(movieCacheMapper::map) }
    }

    override fun getFavoriteMovies(): Flow<List<MovieData>> {
        return db.movieQueries.selectAllMoviesFavorite()
            .asFlow()
            .mapToList()
            .map { movies -> movies.map(movieCacheMapper::map) }
    }

    override fun deleteAllMovies() {
        db.transaction {
            db.movieQueries.deleteAllMovie()
        }
    }

    override fun setMovieFavorite(isFavorite: Boolean, movieId: Long, movieType: String) {
        db.transaction {
            db.movieQueries
                .updateMovieFavorite(isFavorite, movieId, movieType.toMovieType())
        }
    }

    override fun getMovieById(movieId: Long): Flow<MovieData> {
        return db.movieQueries.selectMovieById(movieId)
            .asFlow()
            .mapToOne()
            .map(movieCacheMapper::map)
    }

    private fun String.toMovieType(): MovieType {
        return if (this == "upcoming") {
            MovieType.UPCOMING
        } else {
            MovieType.POPULAR
        }
    }
}
