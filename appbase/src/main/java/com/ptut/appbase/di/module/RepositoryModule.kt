package com.ptut.appbase.di.module

import com.ptut.cache.di.CacheModule
import com.ptut.data.MovieRepositoryImpl
import com.ptut.domain.repository.MovieRepository
import com.ptut.network.di.NetworkModule
import com.ptut.roomdb.di.RoomModule
import dagger.Binds
import dagger.Module

@Module(includes = [ NetworkModule::class,CacheModule::class,RoomModule::class])
abstract class RepositoryModule {
  @Binds
  abstract fun movieRepository(
    movieRepositoryImpl: MovieRepositoryImpl
  ): MovieRepository

}