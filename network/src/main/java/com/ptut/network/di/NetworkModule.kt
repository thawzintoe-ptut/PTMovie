package com.ptut.network.di

import com.ptut.data.dataSource.MovieNetworkDataSource
import com.ptut.network.BuildConfig
import com.ptut.network.MovieNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class, NetworkModule.Provider::class])
abstract class NetworkModule {

  @Binds
  abstract fun networkDataSource(
    movieNetworkDataSourceImpl: MovieNetworkDataSourceImpl
  ): MovieNetworkDataSource

  @Module
  object Provider {

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {

      val okHttpClientBuilder = OkHttpClient.Builder()

      if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
      }

      return okHttpClientBuilder
        .build()
    }

  }

}