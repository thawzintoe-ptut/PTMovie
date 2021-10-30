package com.ptut.data

import com.ptut.data.dataSource.MovieCacheDataSource
import com.ptut.data.dataSource.MovieNetworkDataSource
import com.ptut.data.entity.MovieDataToDomainMapper
import com.ptut.domain.model.MovieDomain
import com.ptut.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieCacheDataSource: MovieCacheDataSource,
    private val movieDataToDomainMapper: MovieDataToDomainMapper
) : MovieRepository {
    override fun getMovieListByType(movieType: String): List<MovieDomain> {
        return movieCacheDataSource.getMoviesByType(movieType)
            .map(movieDataToDomainMapper::map)
    }

    override fun downloadMovies(movieType: String) {
        val data = movieNetworkDataSource.downloadMovies(movieType)
        movieCacheDataSource.putMovies(data)
    }

    override fun setMovieFavorite(isFavorite: Boolean, movieType: String, movieId: Long) {
        movieCacheDataSource.setMovieFavorite(isFavorite, movieId, movieType)
    }

    override fun getMovieFavorite(): List<MovieDomain> {
        return movieCacheDataSource.getFavoriteMovies()
            .map(movieDataToDomainMapper::map)
    }

    override fun getMovieById(movieId: Long): MovieDomain {
        val movieDetail = movieCacheDataSource.getMovieById(movieId)
        return movieDataToDomainMapper.map(movieDetail)
    }



}