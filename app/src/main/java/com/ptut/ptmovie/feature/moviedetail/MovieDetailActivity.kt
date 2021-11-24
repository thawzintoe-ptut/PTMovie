package com.ptut.ptmovie.feature.moviedetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ptut.appbase.core.mvp.MvpActivity
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.ptmovie.R
import com.ptut.ptmovie.databinding.ActivityMovieDetailBinding
import org.threeten.bp.format.DateTimeFormatter

class MovieDetailActivity :
    MvpActivity<ActivityMovieDetailBinding, MovieDetailView, MovieDetailViewModel>(),
    MovieDetailView {
    override val binding: ActivityMovieDetailBinding by lazy {
        ActivityMovieDetailBinding.inflate(
            layoutInflater
        )
    }
    override val viewModel: MovieDetailViewModel by contractedViewModel()
    private var movieId: Long = -1
    private var transitionName: String = ""
    private var movieDetail: MovieDetailVO? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()
        if (intent.hasExtra(MOVIE_ID))
            movieId = intent.getLongExtra(MOVIE_ID, -1L)
        transitionName = intent.getStringExtra(MOVIE_TRANSITION_NAME) ?: ""
        if (movieId != -1L) {
            viewModel.getMovieDetailById(movieId)
            binding.ivMoviePoster.transitionName = transitionName
        }
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.cbFavorite.setOnClickListener {
            if (movieDetail!!.isFavorite) {
                viewModel.requestFavorite(false, movieDetail!!.id, movieDetail!!.movieType)
            } else {
                viewModel.requestFavorite(true, movieDetail!!.id, movieDetail!!.movieType)
            }
        }
        binding.viewNoDetailData.btnEmpty.setOnClickListener {
            if (movieId != -1L) {
                handleDetailUI(loadingDetailView = View.VISIBLE)
                viewModel.getMovieDetailById(movieId)
            }
        }
    }

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TRANSITION_NAME = "movie_transition_name"
    }

    override fun subscribeMovieDetail(movieListLD: LiveData<AsyncViewResource<MovieDetailVO>>) {
        movieListLD.observe(this, {
            when (it) {
                is AsyncViewResource.Loading -> {
                    handleDetailUI(loadingDetailView = View.VISIBLE)
                }
                is AsyncViewResource.Success -> {
                    handleDetailUI(llDetailView = View.VISIBLE)
                    bindData(it.value)
                }
                is AsyncViewResource.Error -> {
                    handleDetailUI(errorDetailView = View.VISIBLE)
                    binding.viewNoDetailData.ivEmpty.setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.ic_movie_server_error)
                    )
                    binding.viewNoDetailData.tvEmpty.text = resources.getString(R.string.something_went_wrong)
                }
            }
        })
    }

    private fun bindData(movieDetailVO: MovieDetailVO) {
        movieDetail = movieDetailVO
        val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movieDetailVO.posterPath}")
            .dontAnimate()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }
            })
            .into(binding.ivMoviePoster)
        binding.tvMovieOriginalTitle.text = movieDetailVO.originalTitle
        binding.tvMovieReleaseDate.text = movieDetailVO.releaseDate.format(dateFormatter)
        binding.tvMovieOverview.text = movieDetailVO.overview
        binding.ratingVoteAverage.rating = movieDetailVO.voteAverage / 2
        binding.tvPopularity.text = movieDetailVO.popularity.toString()
        binding.tvVoteCount.text = movieDetailVO.voteCount.toString()
        if (movieDetailVO.isFavorite) {
            binding.cbFavorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_filled)
            )
        } else {
            binding.cbFavorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
            )
        }
    }

    private fun handleDetailUI(
        llDetailView: Int = View.GONE,
        loadingDetailView: Int = View.GONE,
        errorDetailView: Int = View.GONE
    ) {
        binding.llMovieDetail.visibility = llDetailView
        binding.viewDetailLoading.clLoading.visibility = loadingDetailView
        binding.viewNoDetailData.rlViewEmpty.visibility = errorDetailView
    }
}
