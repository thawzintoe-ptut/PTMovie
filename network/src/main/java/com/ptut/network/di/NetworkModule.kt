package com.ptut.network.di

import com.ptut.data.dataSource.MovieNetworkDataSource
import com.ptut.network.BuildConfig
import com.ptut.network.MovieApi
import com.ptut.network.MovieNetworkDataSourceImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule.Provider::class])
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
//                okHttpClientBuilder.addInterceptor(ChuckInterceptor(application.applicationContext).showNotification(true))
            }

            return okHttpClientBuilder
                .build()
        }

        @Provides
        @Singleton fun retrofit(
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

        @Provides
        @Singleton
        fun devconYangonApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }
    }
}
