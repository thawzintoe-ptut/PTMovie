package com.ptut.roomdb.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.ptut.roomdb.BaseDao
import com.ptut.roomdb.db.entity.MovieCache
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao : BaseDao<MovieCache> {
    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieCache>>

    @Query("SELECT * FROM movie WHERE  movieType LIKE :movieType")
    fun getMoviesByType(movieType: String): Flow<List<MovieCache>>

    @Query("SELECT * FROM movie WHERE  movieId LIKE :movieId")
    fun getMovieById(movieId: Long): Flow<MovieCache>

    @Query("SELECT * FROM movie WHERE  isFavorite LIKE :isFavorite")
    fun getFavoriteMovies(isFavorite: Boolean): Flow<List<MovieCache>>

    @Query("UPDATE Movie SET isFavorite = :isFavorite WHERE movieId =:movieId AND movieType=:movieType")
    fun setFavoriteMovie(isFavorite: Boolean, movieId: Long, movieType: String)
}