package com.ptut.roomdb.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ptut.data.entity.MovieData
import com.ptut.domain.mapper.BidirectionalMap
import com.ptut.domain.usecase.RequestMovieFavorite
import com.ptut.roomdb.db.DbConstants
import com.squareup.moshi.Json
import org.threeten.bp.LocalDate
import javax.inject.Inject

@Entity(tableName = DbConstants.movie_db_name)
data class MovieCache (
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String,
    @ColumnInfo(name = "genreIds")
    val genreIds: List<Long>,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movieId")
    val id: Long,
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String,
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "posterPath")
    val posterPath: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: LocalDate,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Float,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "movieType")
    val movieType: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
)

class MovieRoomCacheMapper @Inject constructor():BidirectionalMap<MovieData,MovieCache>{
    override fun map(item: MovieData): MovieCache {
        return MovieCache(
            posterPath = item.posterPath,
            originalTitle = item.originalTitle,
            releaseDate = item.releaseDate,
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
            isFavorite = item.isFavorite,
            movieType = item.movieType
        )
    }

    override fun reverseMap(item: MovieCache): MovieData {
        return MovieData(
            posterPath = item.posterPath,
            originalTitle = item.originalTitle,
            releaseDate = item.releaseDate,
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
            isFavorite = item.isFavorite,
            movieType = item.movieType
        )
    }

}