package com.ptut.data.dataSource

import com.ptut.data.entity.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieCacheDataSource {
    fun putMovies(movies:List<MovieData>)
    fun getMoviesByType(movieType:String):Flow<List<MovieData>>
    fun getFavoriteMovies():Flow<List<MovieData>>
    fun deleteAllMovies()
    fun setMovieFavorite(isFavorite:Boolean,movieId:Long,movieType: String)
    fun getMovieById(movieId: Long):Flow<MovieData>
}