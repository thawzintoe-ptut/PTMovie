package com.ptut.roomdb.db

import androidx.room.RoomDatabase

import androidx.room.Database
import com.ptut.roomdb.db.dao.MovieDao

@Database(entities = [], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao():MovieDao?
}