package com.ptut.ptmovie.di

import android.app.Application
import android.content.Context
import com.ptut.appbase.di.module.BaseAppModule
import com.ptut.domain.usecase.GetMoviesRequest
import com.ptut.ptmovie.di.AppModule.Provider
import com.ptut.ptmovie.feature.favorite.FavoriteFeatureModule
import com.ptut.ptmovie.feature.home.HomeFeatureModule
import com.ptut.ptmovie.feature.moviedetail.MovieDetailFeatureModule
import com.ptut.ptmovie.feature.splash.SplashFeatureModule
import com.ptut.ptmovie.feature.startup.StartupFeatureModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        Provider::class,
        BaseAppModule::class,
        HomeFeatureModule::class,
        FavoriteFeatureModule::class,
        SplashFeatureModule::class,
        MovieDetailFeatureModule::class,
        StartupFeatureModule::class
    ]
)
abstract class AppModule {

    @Module
    object Provider {
        @Provides
        fun context(application: Application): Context {
            return application.applicationContext
        }


    }
}