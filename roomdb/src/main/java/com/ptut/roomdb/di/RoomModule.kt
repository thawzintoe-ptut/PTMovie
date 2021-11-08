package com.ptut.roomdb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

import androidx.room.Room
import com.ptut.roomdb.db.AppDatabase
import com.ptut.roomdb.db.dao.MovieDao


@Module(includes = [RoomModule.Providers::class])
abstract class RoomModule {


    @Module
    internal object  Providers{
        @DatabaseInfo
        val mDBName = "test_database.db"

        @Provides
        @Singleton
        fun provideDatabase(context:Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "PTMovieDB"
            ).fallbackToDestructiveMigration().build()
        }

        @Provides
        @DatabaseInfo
        fun provideDatabaseName(): String {
            return mDBName
        }

        @Singleton
        @Provides
        fun provideMovieDao(db: AppDatabase): MovieDao? {
            return db.movieDao()
        }

    }
}