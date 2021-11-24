package com.ptut.ptmovie.feature.home

import androidx.lifecycle.ViewModel
import com.ptut.appbase.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeFeatureModule {

    @ContributesAndroidInjector
    abstract fun movieHomeFragment(): MovieHomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieHomeViewModel::class)
    abstract fun movieHomeViewModel(movieHomeViewModel: MovieHomeViewModel): ViewModel
}
