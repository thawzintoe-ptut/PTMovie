package com.ptut.ptmovie.feature.splash

import androidx.lifecycle.viewModelScope
import com.ptut.appbase.core.mvp.BaseViewModel
import com.ptut.domain.usecase.GetMoviesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val getMoviesRequest: GetMoviesRequest
) : BaseViewModel<SplashView>() {
    fun downloadMovies(movieType:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getMoviesRequest.execute(movieType)
            }catch (t:Throwable){
                Timber.e(t)
            }
        }
    }
}