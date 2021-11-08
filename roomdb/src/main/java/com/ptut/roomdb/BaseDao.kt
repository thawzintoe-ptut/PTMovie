package com.ptut.roomdb

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(t: List<T>)
    @Update
    fun update(t: T)
    @Delete
    fun delete(t: T)
    @Query("DELETE FROM movie")
    fun deleteAllMovies()
}