package com.ptut.ptmovie.feature.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ptut.appbase.core.mvp.BaseViewModel
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.domain.usecase.GetFavoriteMovies
import com.ptut.domain.usecase.RequestMovieFavorite
import com.ptut.ptmovie.feature.favorite.MovieFavoriteView
import com.ptut.ptmovie.feature.home.MovieDomainToVOMapper
import com.ptut.ptmovie.feature.home.MovieVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFavoriteViewModel @Inject constructor(
    private val getFavoriteMovies: GetFavoriteMovies,
    private val requestMovieFavorite: RequestMovieFavorite,
    private val movieMapper: MovieDomainToVOMapper
):BaseViewModel<MovieFavoriteView>(){
    private val _movieFavoriteListLD = MutableLiveData<AsyncViewResource<List<MovieVO>>>()
    private val movieFavoriteListLD: LiveData<AsyncViewResource<List<MovieVO>>> = _movieFavoriteListLD
    override fun attachView(viewable: MovieFavoriteView) {
        super.attachView(viewable)
        view?.subscribeFavoriteMovies(movieFavoriteListLD)
    }

    fun getFavoriteMovieList(){
        viewModelScope.launch(Dispatchers.IO){
            _movieFavoriteListLD.postValue(AsyncViewResource.Loading())
                try {
                    getFavoriteMovies.execute(Unit).collect { movieList ->
                        val movies = movieList.map { movieMapper.map(it) }
                        _movieFavoriteListLD.postValue(AsyncViewResource.Success(movies))
                    }
                }catch (t:Throwable){
                    _movieFavoriteListLD.postValue(AsyncViewResource.Error(t))
                }
        }
    }

    fun requestFavorite(isFavorite:Boolean,movieId:Long,movieType:String){
        viewModelScope.launch(Dispatchers.IO) {
            requestMovieFavorite.execute(
                RequestMovieFavorite.Params(isFavorite,movieId, movieType))
            _movieFavoriteListLD.postValue(AsyncViewResource.Loading())
            try {
                getFavoriteMovies.execute(Unit).collect { movieList ->
                    val movies = movieList.map { movieMapper.map(it) }
                    _movieFavoriteListLD.postValue(AsyncViewResource.Success(movies))
                }
            }catch (t:Throwable){
                _movieFavoriteListLD.postValue(AsyncViewResource.Error(t))
            }
        }
    }
}