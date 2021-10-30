package com.ptut.domain.repository

import com.ptut.domain.model.MovieDomain

interface MovieRepository {
    fun getMovieListByType(movieType:String):List<MovieDomain>
    fun downloadMovies(movieType: String)
    fun setMovieFavorite(isFavorite:Boolean,movieType: String,movieId:Long)
    fun getMovieFavorite():List<MovieDomain>
    fun getMovieById(movieId: Long):MovieDomain
}