package com.ptut.ptmovie.feature.moviedetail

import androidx.lifecycle.LiveData
import com.ptut.appbase.core.mvp.Viewable
import com.ptut.appbase.helper.AsyncViewResource

interface MovieDetailView : Viewable {
    fun subscribeMovieDetail(movieListLD: LiveData<AsyncViewResource<MovieDetailVO>>)
}
