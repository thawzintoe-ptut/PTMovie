package com.ptut.roomdb.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ptut.roomdb.BaseDao
import com.ptut.roomdb.db.entity.MovieCache
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao : BaseDao<MovieCache> {
    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieCache>>

    @Query("SELECT * FROM movie WHERE  movie_type LIKE :movieType")
    fun getMoviesByType(movieType: String?): Flow<List<MovieCache>>
}