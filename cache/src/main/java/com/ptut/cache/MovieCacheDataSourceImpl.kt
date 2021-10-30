package com.ptut.cache

import com.ptut.cache.database.AppDatabase
import com.ptut.cache.mapper.MovieCacheToDataMapper
import com.ptut.data.dataSource.MovieCacheDataSource
import com.ptut.data.entity.MovieData
import javax.inject.Inject

class MovieCacheDataSourceImpl @Inject constructor(
    private val db:AppDatabase,
    private val movieCacheMapper:MovieCacheToDataMapper
):MovieCacheDataSource {
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

    override fun getMoviesByType(movieType: String): List<MovieData> {
        return db.movieQueries.selectAllMoviesByType(movieType.toMovieType())
            .executeAsList()
            .map(movieCacheMapper::map)
    }

    override fun getFavoriteMovies(): List<MovieData> {
        return db.movieQueries.selectAllMoviesFavorite().executeAsList().
                map(movieCacheMapper::map)
    }

    override fun deleteAllMovies() {
        db.transaction {
            db.movieQueries.deleteAllMovie()
        }
    }

    override fun setMovieFavorite(isFavorite:Boolean,movieId: Long, movieType: String) {
        db.transaction {
           db.movieQueries
                .updateMovieFavorite(isFavorite,movieId,movieType.toMovieType())
        }
    }

    override fun getMovieById(movieId: Long): MovieData {
        val movieDetail = db.movieQueries.selectMovieById(movieId).executeAsOne()
        return movieCacheMapper.map(movieDetail)
    }

    private fun String.toMovieType():MovieType{
        return if(this == "upcoming"){
            MovieType.UPCOMING
        }else{
            MovieType.POPULAR
        }
    }
}