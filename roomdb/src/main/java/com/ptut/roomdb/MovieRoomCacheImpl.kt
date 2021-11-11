package com.ptut.roomdb

import com.ptut.data.dataSource.MovieRoomDataSource
import com.ptut.data.entity.MovieData
import com.ptut.domain.model.MovieDomain
import com.ptut.domain.repository.MovieRepository
import com.ptut.roomdb.db.dao.MovieDao
import javax.inject.Inject

class MovieRoomCacheImpl @Inject constructor(
    private val movieDao: MovieDao
){
}