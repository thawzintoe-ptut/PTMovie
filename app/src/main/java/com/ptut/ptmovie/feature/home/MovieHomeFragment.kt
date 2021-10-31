package com.ptut.ptmovie.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.LiveData
import com.ptut.appbase.core.mvp.MvpFragment
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.ptmovie.R
import com.ptut.ptmovie.databinding.FragmentMovieHomeBinding
import com.ptut.ptmovie.feature.home.adapter.ClickFavoriteMovieListener
import com.ptut.ptmovie.feature.home.adapter.MovieAdapter
import com.ptut.ptmovie.feature.home.adapter.MovieUpcomingAdapter
import com.ptut.ptmovie.feature.moviedetail.MovieDetailActivity
import com.ptut.ptmovie.feature.utils.initializeGrid
import com.ptut.ptmovie.feature.utils.initializeHorizontal

const val MOVIE_UPCOMING = "upcoming"
const val MOVIE_POPULAR = "popular"
class MovieHomeFragment :
    MvpFragment<FragmentMovieHomeBinding, MovieHomeView, MovieHomeViewModel>(),
    MovieHomeView,
    ClickFavoriteMovieListener,
    View.OnClickListener {
    override val viewModel: MovieHomeViewModel by contractedViewModel()
    override fun bindView(inflater: LayoutInflater): FragmentMovieHomeBinding =
        FragmentMovieHomeBinding.inflate(inflater)

    private val adapter by lazy { MovieAdapter(this) }
    private val upComingAdapter by lazy { MovieUpcomingAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        listeners()
    }

    private fun listeners() {
        binding.viewNoUpcomingData.btnEmpty.setOnClickListener(this)
        binding.viewNoPopularData.btnEmpty.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        binding.rvUpcomingMovie.initializeHorizontal()
        binding.rvUpcomingMovie.adapter = upComingAdapter
        binding.rvPopularMovie.initializeGrid(2)
        binding.rvPopularMovie.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUpcomingMovies()
        viewModel.getPopularMovies()
    }

    override fun subscribeUpcomingMovie(movieListLD: LiveData<AsyncViewResource<List<MovieVO>>>) {
        movieListLD.observe(viewLifecycleOwner, { asyncResult ->
            when (asyncResult) {
                is AsyncViewResource.Loading -> {
                    handleUpComingUI(loadingUpcomingView = View.VISIBLE)
                }
                is AsyncViewResource.Success -> {
                    val data = asyncResult.value
                    if (data.isNotEmpty()) {
                        handleUpComingUI(rvUpcomingView = View.VISIBLE)
                        upComingAdapter.submitList(data)
                    } else {
                        handleUpComingUI(errorUpComingView = View.VISIBLE)
                        binding.viewNoUpcomingData.ivEmpty.setImageDrawable(
                            ContextCompat.getDrawable(requireContext(), R.drawable.ic_no_movie)
                        )
                        binding.viewNoUpcomingData.tvEmpty.text =
                            resources.getString(R.string.no_data)
                    }

                }
                is AsyncViewResource.Error -> {
                    handleUpComingUI(errorUpComingView = View.VISIBLE)
                    binding.viewNoUpcomingData.ivEmpty.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_movie_server_error
                        )
                    )
                    binding.viewNoUpcomingData.tvEmpty.text =
                        resources.getString(R.string.something_went_wrong)
                }
            }
        })
    }

    override fun subscribePopularMovie(movieListLD: LiveData<AsyncViewResource<List<MovieVO>>>) {
        movieListLD.observe(viewLifecycleOwner, { asyncResult ->
            when (asyncResult) {
                is AsyncViewResource.Loading -> {
                    handlePopularUI(loadingPopularView = View.VISIBLE)
                }
                is AsyncViewResource.Success -> {
                    val data = asyncResult.value
                    if (data.isNotEmpty()) {
                        handlePopularUI(rvPopularView = View.VISIBLE)
                        adapter.submitList(data)
                    } else {
                        handlePopularUI(errorPopularView = View.VISIBLE)
                        binding.viewNoPopularData.ivEmpty.setImageDrawable(
                            ContextCompat.getDrawable(requireContext(), R.drawable.ic_no_movie)
                        )
                        binding.viewNoPopularData.tvEmpty.text =
                            resources.getString(R.string.no_data)
                    }
                }
                is AsyncViewResource.Error -> {
                    handlePopularUI(errorPopularView = View.VISIBLE)
                    binding.viewNoPopularData.ivEmpty.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_movie_server_error
                        )
                    )
                    binding.viewNoPopularData.tvEmpty.text =
                        resources.getString(R.string.something_went_wrong)
                }
            }
        })
    }

    override fun onClickFavoriteMovie(id: Long, isFavorite: Boolean, movieType: String) {
        viewModel.requestFavorite(isFavorite, id, movieType)
    }

    override fun onClickMovieDetail(movieId: Long, view: AppCompatImageView) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID, movieId)
        intent.putExtra(
            MovieDetailActivity.MOVIE_TRANSITION_NAME, ViewCompat.getTransitionName(view)
        )
        val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            view,
            ViewCompat.getTransitionName(view)!!
        )
        startActivity(intent, options.toBundle())
    }

    private fun handleUpComingUI(
        rvUpcomingView: Int = View.GONE,
        loadingUpcomingView: Int = View.GONE,
        errorUpComingView: Int = View.GONE
    ) {
        binding.rvUpcomingMovie.visibility = rvUpcomingView
        binding.viewUpcomingLoading.clLoading.visibility = loadingUpcomingView
        binding.viewNoUpcomingData.rlViewEmpty.visibility = errorUpComingView
    }

    private fun handlePopularUI(
        rvPopularView: Int = View.GONE,
        loadingPopularView: Int = View.GONE,
        errorPopularView: Int = View.GONE
    ) {
        binding.rvPopularMovie.visibility = rvPopularView
        binding.viewPopularLoading.clLoading.visibility = loadingPopularView
        binding.viewNoPopularData.rlViewEmpty.visibility = errorPopularView
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.viewNoUpcomingData.btnEmpty -> {
                handleUpComingUI(loadingUpcomingView = View.VISIBLE)
                viewModel.downloadMovies(MOVIE_UPCOMING)
            }
            binding.viewNoPopularData.btnEmpty -> {
                handlePopularUI(loadingPopularView = View.VISIBLE)
                viewModel.downloadMovies(MOVIE_POPULAR)
            }
        }
    }

}