package com.ptut.ptmovie.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ptut.appbase.core.mvp.BaseViewModel
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.domain.usecase.GetMovieUpComing
import com.ptut.domain.usecase.GetMoviesRequest
import com.ptut.domain.usecase.RequestMovieFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieHomeViewModel @Inject constructor(
    private val getMovies: GetMovieUpComing,
    private val requestMovieFavorite: RequestMovieFavorite,
    private val getMoviesRequest: GetMoviesRequest
) : BaseViewModel<MovieHomeView>() {

    private val _movieUpComingListLD = MutableLiveData<AsyncViewResource<List<MovieVO>>>()
    private val movieUpComingListLD: LiveData<AsyncViewResource<List<MovieVO>>> =
        _movieUpComingListLD
    private val _moviePopularListLD = MutableLiveData<AsyncViewResource<List<MovieVO>>>()
    private val moviePopularListLD: LiveData<AsyncViewResource<List<MovieVO>>> = _moviePopularListLD

    override fun attachView(viewable: MovieHomeView) {
        super.attachView(viewable)
        view?.subscribeUpcomingMovie(movieUpComingListLD)
        view?.subscribePopularMovie(moviePopularListLD)
    }

    fun getUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies.execute("upcoming")
                .map { it.map(MovieDomainToVOMapper::map) }
                .catch { _movieUpComingListLD.postValue(AsyncViewResource.Error(it)) }
                .onStart { _movieUpComingListLD.postValue(AsyncViewResource.Loading()) }
                .collect { movieList ->
                    _movieUpComingListLD.postValue(AsyncViewResource.Success(movieList))
                }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovies.execute("popular")
                .map { it.map(MovieDomainToVOMapper::map) }
                .catch { _moviePopularListLD.postValue(AsyncViewResource.Error(it)) }
                .onStart { _moviePopularListLD.postValue(AsyncViewResource.Loading()) }
                .collect { movieList ->
                    _moviePopularListLD.postValue(AsyncViewResource.Success(movieList))
                }
        }
    }

    fun requestFavorite(isFavorite: Boolean, movieId: Long, movieType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            requestMovieFavorite.execute(
                RequestMovieFavorite.Params(isFavorite, movieId, movieType)
            )
        }
    }

    fun downloadMovies(movieType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getMoviesRequest.execute(movieType)
                if (movieType == MOVIE_UPCOMING) {
                    _movieUpComingListLD.postValue(AsyncViewResource.Loading())
                    getMovies.execute(MOVIE_UPCOMING)
                        .map { it.map(MovieDomainToVOMapper::map) }
                        .collect { movieList ->
                        _movieUpComingListLD.postValue(AsyncViewResource.Success(movieList))
                    }
                } else {
                    _moviePopularListLD.postValue(AsyncViewResource.Loading())
                    getMovies.execute(MOVIE_POPULAR)
                        .map { it.map(MovieDomainToVOMapper::map) }
                        .collect { movieList ->
                        _moviePopularListLD.postValue(AsyncViewResource.Success(movieList))
                    }
                }
            } catch (t: Throwable) {
                Timber.e(t)
                if (movieType == MOVIE_UPCOMING) {
                    _movieUpComingListLD.postValue(AsyncViewResource.Error(t))
                } else {
                    _moviePopularListLD.postValue(AsyncViewResource.Error(t))
                }
            }
        }
    }
}
