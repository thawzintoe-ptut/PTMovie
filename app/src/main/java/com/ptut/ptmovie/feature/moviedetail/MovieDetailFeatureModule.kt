package com.ptut.ptmovie.feature.moviedetail

import androidx.lifecycle.ViewModel
import com.ptut.appbase.di.viewmodel.ViewModelKey
import com.ptut.ptmovie.feature.splash.SplashActivity
import com.ptut.ptmovie.feature.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailFeatureModule {
    @ContributesAndroidInjector
    abstract fun movieDetailActivity(): MovieDetailActivity

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun movieDetailViewModel(splashViewModel: MovieDetailViewModel): ViewModel
}