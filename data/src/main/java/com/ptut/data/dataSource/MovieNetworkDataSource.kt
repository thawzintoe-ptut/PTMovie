package com.ptut.data.dataSource

import com.ptut.data.entity.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieNetworkDataSource {
    fun downloadMovies(movieType: String):List<MovieData>
}