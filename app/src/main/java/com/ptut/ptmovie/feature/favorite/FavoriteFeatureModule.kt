package com.ptut.ptmovie.feature.favorite

import androidx.lifecycle.ViewModel
import com.ptut.appbase.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteFeatureModule {

    @ContributesAndroidInjector
    abstract fun movieFavoriteFragment(): MovieFavoriteFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieFavoriteViewModel::class)
    abstract fun movieFavoriteViewModel(movieFavoriteViewModel: MovieFavoriteViewModel): ViewModel
}
