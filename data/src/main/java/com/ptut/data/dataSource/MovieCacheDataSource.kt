package com.ptut.data.dataSource

import com.ptut.data.entity.MovieData

interface MovieCacheDataSource {
    fun putMovies(movies:List<MovieData>)
    fun getMoviesByType(movieType:String):List<MovieData>
    fun getFavoriteMovies():List<MovieData>
    fun deleteAllMovies()
    fun setMovieFavorite(isFavorite:Boolean,movieId:Long,movieType: String)
    fun getMovieById(movieId: Long):MovieData
}