package com.ptut.ptmovie.feature.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ptut.appbase.core.mvp.BaseViewModel
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.domain.usecase.GetMovieDetail
import com.ptut.domain.usecase.RequestMovieFavorite
import com.ptut.ptmovie.feature.home.MovieVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetail:GetMovieDetail,
    private val requestMovieFavorite: RequestMovieFavorite,
    private val movieDetailMapper:MovieDetailDomainToVOMapper
):BaseViewModel<MovieDetailView>() {
    private val _movieDetailLD:MutableLiveData<AsyncViewResource<MovieDetailVO>> = MutableLiveData()
    private val movieDetailLD:LiveData<AsyncViewResource<MovieDetailVO>> =  _movieDetailLD

    override fun attachView(viewable: MovieDetailView) {
        super.attachView(viewable)
        view?.subscribeMovieDetail(movieDetailLD)
    }
    fun getMovieDetailById(movieId:Long){
        viewModelScope.launch(Dispatchers.IO){
            _movieDetailLD.postValue(AsyncViewResource.Loading())
            try {
                getMovieDetail.execute(movieId).collect { movie ->
                    _movieDetailLD.postValue(
                        AsyncViewResource.Success(
                            movieDetailMapper.map(movie)
                        )
                    )
                }
            }catch (t:Throwable){
                _movieDetailLD.postValue(AsyncViewResource.Error(t))
            }
        }
    }

    fun requestFavorite(isFavorite:Boolean,movieId:Long,movieType:String){
        viewModelScope.launch(Dispatchers.IO) {
            requestMovieFavorite.execute(
                RequestMovieFavorite.Params(isFavorite,movieId, movieType))
        }
    }
}