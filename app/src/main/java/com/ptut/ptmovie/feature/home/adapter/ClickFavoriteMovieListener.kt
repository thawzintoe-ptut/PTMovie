package com.ptut.ptmovie.feature.home.adapter

import androidx.appcompat.widget.AppCompatImageView

interface ClickFavoriteMovieListener {
    fun onClickFavoriteMovie(id: Long, isFavorite: Boolean, movieType: String)
    fun onClickMovieDetail(movieId: Long, view: AppCompatImageView)
}
