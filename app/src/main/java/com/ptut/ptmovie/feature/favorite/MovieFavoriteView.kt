package com.ptut.ptmovie.feature.favorite

import androidx.lifecycle.LiveData
import com.ptut.appbase.core.mvp.Viewable
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.ptmovie.feature.home.MovieVO

interface MovieFavoriteView : Viewable {
    fun subscribeFavoriteMovies(movies: LiveData<AsyncViewResource<List<MovieVO>>>)
}
