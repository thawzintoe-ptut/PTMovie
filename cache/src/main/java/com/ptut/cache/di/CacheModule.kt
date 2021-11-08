package com.ptut.cache.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ptut.cache.MovieCacheDataSourceImpl
import com.ptut.cache.columnadapter.IntegerArrayColumnAdapter
import com.ptut.cache.columnadapter.LocalDateColumnAdapter
import com.ptut.cache.columnadapter.LongArrayColumnAdapter
import com.ptut.cache.database.AppDatabase
import com.ptut.cache.database.Movie
import com.ptut.data.dataSource.MovieCacheDataSource
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [CacheModule.Providers::class])
abstract class CacheModule {

    @Binds
    abstract fun movieCacheDataSource(
        movieCacheDataSourceImpl: MovieCacheDataSourceImpl
    ):MovieCacheDataSource

    @Module
    internal object Providers {

        @Provides
        @Singleton
        fun sqlDriver(context: Context): SqlDriver {
            return AndroidSqliteDriver(AppDatabase.Schema, context, "PtMovie.db")
        }

        @Provides
        @Singleton
        fun database(sqlDriver: SqlDriver): AppDatabase {
            return AppDatabase(
                sqlDriver,
                Movie.Adapter(
                    LongArrayColumnAdapter,
                    LocalDateColumnAdapter,
                    EnumColumnAdapter()
                )
            )
        }

        @Provides
        fun sharedPref(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

    }

}