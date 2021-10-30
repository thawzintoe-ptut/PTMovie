package com.ptut.network.entity

import com.ptut.data.entity.MovieData
import com.ptut.domain.mapper.UnidirectionalMap
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class MovieNetwork(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Long,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int
)

class MovieNetworkToDataMapper @Inject constructor() : UnidirectionalMap<MovieNetwork, MovieData> {
    override fun map(item: MovieNetwork): MovieData {
        val dateTimeFormatter = DateTimeFormatter.ISO_DATE
        return MovieData(
            posterPath = item.posterPath,
            originalTitle = item.originalTitle,
            releaseDate = ZonedDateTime.parse(item.releaseDate, dateTimeFormatter).toLocalDate(),
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
            movieType = "",
            isFavorite = false
        )
    }

}