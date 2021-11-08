package com.ptut.domain.repository

import com.ptut.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieListByType(movieType:String):Flow<List<MovieDomain>>
    fun downloadMovies(movieType: String)
    fun setMovieFavorite(isFavorite:Boolean,movieType: String,movieId:Long)
    fun getMovieFavorite():Flow<List<MovieDomain>>
    fun getMovieById(movieId: Long):Flow<MovieDomain>
}