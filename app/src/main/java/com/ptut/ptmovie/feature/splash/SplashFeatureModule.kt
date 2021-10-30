package com.ptut.ptmovie.feature.splash

import androidx.lifecycle.ViewModel
import com.ptut.appbase.di.viewmodel.ViewModelKey
import com.ptut.ptmovie.feature.home.MovieHomeFragment
import com.ptut.ptmovie.feature.home.MovieHomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SplashFeatureModule {
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(splashViewModel: SplashViewModel): ViewModel
}