package com.ptut.ptmovie.di

import android.app.Application
import android.content.Context
import coil.ImageLoader
import com.ptut.appbase.di.module.BaseAppModule
import dagger.Module
import com.ptut.ptmovie.di.AppModule.Provider
import com.ptut.ptmovie.feature.favorite.FavoriteFeatureModule
import com.ptut.ptmovie.feature.home.HomeFeatureModule
import com.ptut.ptmovie.feature.moviedetail.MovieDetailFeatureModule
import com.ptut.ptmovie.feature.splash.SplashFeatureModule
import dagger.Provides
import org.threeten.bp.Clock
import javax.inject.Singleton

@Module(
    includes = [
        Provider::class,
        BaseAppModule::class,
        HomeFeatureModule::class,
        FavoriteFeatureModule::class,
        SplashFeatureModule::class,
        MovieDetailFeatureModule::class
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