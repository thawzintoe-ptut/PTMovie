package com.ptut.network

import com.ptut.data.dataSource.MovieNetworkDataSource
import com.ptut.data.entity.MovieData
import com.ptut.network.entity.MovieNetwork
import com.ptut.network.entity.MovieNetworkToDataMapper
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class MovieNetworkDataSourceImpl @Inject constructor(
    private val api: MovieApi,
    private val movieNetworkToDataMapper: MovieNetworkToDataMapper
) : MovieNetworkDataSource {
    override fun downloadMovies(movieType: String): List<MovieData> {
        val movies =
            api.getMovie(movieType, BuildConfig.API_KEY).executeOrThrow()
        return map(movies.results, movieType)
    }

    fun map(movieList: List<MovieNetwork>, movieType: String): List<MovieData> {
        val data = mutableListOf<MovieData>()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        movieList.forEach { item ->
            val movie = MovieData(
                posterPath = item.posterPath,
                originalTitle = item.originalTitle,
                releaseDate = LocalDate.parse(item.releaseDate, dateTimeFormatter),
                id = item.id,
                voteAverage = item.voteAverage,
                adult = item.adult,
                backdropPath = item.backdropPath,
                genreIds = item.genreIds,
                originalLanguage = item.originalLanguage,
                overview = item.overview,
                popularity = item.popularity,
                title = item.title,
                video = item.video,
                voteCount = item.voteCount,
                movieType = movieType,
                isFavorite = false
            )
            data.add(movie)
        }
        return data
    }
}

