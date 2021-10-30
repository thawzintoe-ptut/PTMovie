package com.ptut.ptmovie.feature.favorite

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
import com.ptut.ptmovie.databinding.FragmentMovieFavoriteBinding
import com.ptut.ptmovie.feature.home.MovieVO
import com.ptut.ptmovie.feature.home.adapter.ClickFavoriteMovieListener
import com.ptut.ptmovie.feature.home.adapter.MovieAdapter
import com.ptut.ptmovie.feature.moviedetail.MovieDetailActivity
import com.ptut.ptmovie.feature.utils.initializeGrid

class MovieFavoriteFragment :
    MvpFragment<FragmentMovieFavoriteBinding, MovieFavoriteView, MovieFavoriteViewModel>(),
    MovieFavoriteView,ClickFavoriteMovieListener {
    override val viewModel: MovieFavoriteViewModel by contractedViewModel()
    override fun bindView(inflater: LayoutInflater): FragmentMovieFavoriteBinding {
        return FragmentMovieFavoriteBinding.inflate(inflater)
    }
    private val adapter by lazy { MovieAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavoriteMovie.initializeGrid(2)
        binding.rvFavoriteMovie.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovieList()
    }

    override fun subscribeFavoriteMovies(movies: LiveData<AsyncViewResource<List<MovieVO>>>) {
        movies.observe(viewLifecycleOwner, { asyncResult ->
            when (asyncResult) {
                is AsyncViewResource.Loading -> {
                    handleUpComingUI(loadingFavoriteView = View.VISIBLE)
                }
                is AsyncViewResource.Success -> {
                    val data = asyncResult.value
                    if(data.isNotEmpty()){
                        handleUpComingUI(rvFavoriteView = View.VISIBLE)
                        adapter.submitList(data)
                    }else{
                        handleUpComingUI(errorFavoriteView = View.VISIBLE)
                        binding.viewNoFavoriteData.ivEmpty.setImageDrawable(
                            ContextCompat.getDrawable(requireContext(), R.drawable.ic_no_movie))
                        binding.viewNoFavoriteData.tvEmpty.text = resources.getString(R.string.no_favorite_data)
                        binding.viewNoFavoriteData.btnEmpty.visibility = View.GONE
                    }
                }
                is AsyncViewResource.Error -> {
                    binding.viewNoFavoriteData.ivEmpty.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_movie_server_error))
                    binding.viewNoFavoriteData.tvEmpty.text = resources.getString(R.string.something_went_wrong)
                    binding.viewNoFavoriteData.btnEmpty.visibility = View.GONE
                }
            }
        })
    }

    override fun onClickFavoriteMovie(id: Long, isFavorite: Boolean, movieType: String) {
        viewModel.requestFavorite(isFavorite,id,movieType)
    }

    override fun onClickMovieDetail(movieId: Long,view: AppCompatImageView) {
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
        rvFavoriteView:Int = View.GONE,
        loadingFavoriteView:Int = View.GONE,
        errorFavoriteView:Int = View.GONE
    ){
        binding.rvFavoriteMovie.visibility = rvFavoriteView
        binding.viewFavoriteLoading.clLoading.visibility = loadingFavoriteView
        binding.viewNoFavoriteData.rlViewEmpty.visibility = errorFavoriteView
    }

}