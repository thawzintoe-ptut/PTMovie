package com.ptut.roomdb

import com.ptut.data.dataSource.MovieCacheDataSource
import com.ptut.data.entity.MovieData
import com.ptut.domain.model.MovieDomain
import com.ptut.domain.repository.MovieRepository
import com.ptut.roomdb.db.dao.MovieDao
import com.ptut.roomdb.db.entity.MovieRoomCacheMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRoomCacheImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieCacheMapper: MovieRoomCacheMapper
):MovieCacheDataSource{
    override fun putMovies(movies: List<MovieData>) {
        movieDao.insertAll(movies.map(movieCacheMapper::map))
    }

    override fun getMoviesByType(movieType: String): Flow<List<MovieData>> {
        return  movieDao.getMoviesByType(movieType)
            .map { movies -> movies.map(movieCacheMapper::reverseMap) }
    }

    override fun getFavoriteMovies(): Flow<List<MovieData>> {
       return movieDao.getFavoriteMovies(true)
           .map { movies -> movies.map(movieCacheMapper::reverseMap) }
    }

    override fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }

    override fun setMovieFavorite(isFavorite: Boolean, movieId: Long, movieType: String) {
        movieDao.setFavoriteMovie(isFavorite, movieId, movieType)
    }

    override fun getMovieById(movieId: Long): Flow<MovieData> {
        return movieDao.getMovieById(movieId)
            .map(movieCacheMapper::reverseMap)
    }

}