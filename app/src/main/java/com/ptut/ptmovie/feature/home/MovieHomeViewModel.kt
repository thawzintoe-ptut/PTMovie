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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieHomeViewModel @Inject constructor(
    private val getMovies: GetMovieUpComing,
    private val requestMovieFavorite: RequestMovieFavorite,
    private val getMoviesRequest: GetMoviesRequest,
    private val movieMapper: MovieDomainToVOMapper
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
            _movieUpComingListLD.postValue(AsyncViewResource.Loading())
            try {
                getMovies.execute("upcoming").collect { movieList ->
                    val movies = movieList.map { movieMapper.map(it) }
                    _movieUpComingListLD.postValue(AsyncViewResource.Success(movies))
                }
            } catch (t: Throwable) {
                _movieUpComingListLD.postValue(AsyncViewResource.Error(t))
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviePopularListLD.postValue(AsyncViewResource.Loading())
            try {
                getMovies.execute("popular").collect { movieList ->
                    val movies = movieList.map { movieMapper.map(it) }
                    _moviePopularListLD.postValue(AsyncViewResource.Success(movies))
                }
            } catch (t: Throwable) {
                _moviePopularListLD.postValue(AsyncViewResource.Error(t))
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
               if(movieType == MOVIE_UPCOMING){
                   _movieUpComingListLD.postValue(AsyncViewResource.Loading())
                   getMovies.execute(MOVIE_UPCOMING).collect { movieList ->
                       val movies = movieList.map { movieMapper.map(it) }
                       _movieUpComingListLD.postValue(AsyncViewResource.Success(movies))
                   }
               }else{
                   _moviePopularListLD.postValue(AsyncViewResource.Loading())
                   getMovies.execute(MOVIE_POPULAR).collect { movieList ->
                       val movies = movieList.map { movieMapper.map(it) }
                       _moviePopularListLD.postValue(AsyncViewResource.Success(movies))
                   }
               }
            }catch (t:Throwable){
                Timber.e(t)
                if(movieType == MOVIE_UPCOMING){
                    _movieUpComingListLD.postValue(AsyncViewResource.Error(t))
                }else{
                    _moviePopularListLD.postValue(AsyncViewResource.Error(t))
                }
            }
        }
    }

}