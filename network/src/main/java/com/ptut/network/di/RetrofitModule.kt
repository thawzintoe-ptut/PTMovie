package com.ptut.network.di

import com.ptut.network.BuildConfig
import com.ptut.network.MovieApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import com.ptut.network.di.RetrofitModule.Provider
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [Provider::class])
abstract class RetrofitModule {

  @Module
  object Provider {

    @Provides @Singleton fun retrofit(
      okHttpClient: OkHttpClient
    ): Retrofit {

      val baseUrl = BuildConfig.BASE_URL

      val moshi = Moshi.Builder()
        .build()

      return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
    }

    @Provides fun devconYangonApi(retrofit: Retrofit): MovieApi {
      return retrofit.create(MovieApi::class.java)
    }
  }

}