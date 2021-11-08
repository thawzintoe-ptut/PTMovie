package com.ptut.roomdb.db

import androidx.room.RoomDatabase

import androidx.room.Database
import androidx.room.TypeConverters
import com.ptut.roomdb.db.dao.MovieDao
import com.ptut.roomdb.db.typeConverter.LocalDateConverter

@Database(entities = [], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao():MovieDao?
}