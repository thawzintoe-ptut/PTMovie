package com.ptut.ptmovie.feature.home

import androidx.lifecycle.LiveData
import com.ptut.appbase.core.mvp.Viewable
import com.ptut.appbase.helper.AsyncViewResource

interface MovieHomeView : Viewable {
    fun subscribeUpcomingMovie(movieListLD: LiveData<AsyncViewResource<List<MovieVO>>>)
    fun subscribePopularMovie(movieListLD: LiveData<AsyncViewResource<List<MovieVO>>>)
}
