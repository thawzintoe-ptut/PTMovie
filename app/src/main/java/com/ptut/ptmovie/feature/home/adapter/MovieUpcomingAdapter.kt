package com.ptut.ptmovie.feature.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ptut.appbase.core.recyclerview.BaseRecyclerViewAdapter
import com.ptut.appbase.core.recyclerview.BaseViewHolder
import com.ptut.appbase.core.recyclerview.RecyclerViewItemClickListener
import com.ptut.appbase.core.recyclerview.diffCallBackWith
import com.ptut.appbase.helper.inflater
import com.ptut.ptmovie.R
import com.ptut.ptmovie.databinding.ListItemMoviePopularBinding
import com.ptut.ptmovie.feature.home.MovieVO
import javax.inject.Inject

class MovieUpcomingAdapter @Inject constructor(
    private val clickFavoriteMovieListener: ClickFavoriteMovieListener
) : BaseRecyclerViewAdapter<MovieVO, MovieUpcomingViewHolder>(
    diffCallback = diffCallBackWith(
        areItemTheSame = { oldItem, newItem ->
            oldItem.id == newItem.id
        },
        areContentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {
    private val clickListener = object : RecyclerViewItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            when (view.id) {
                R.id.cb_favorite -> {
                    if (getItem(position).isFavorite) {
                        clickFavoriteMovieListener.onClickFavoriteMovie(
                            getItem(position).id, false, getItem(position).movieType
                        )
                    } else {
                        clickFavoriteMovieListener.onClickFavoriteMovie(
                            getItem(position).id, true, getItem(position).movieType
                        )
                    }
                }
                R.id.iv_movie_poster ->
                    clickFavoriteMovieListener.onClickMovieDetail(getItem(position).id, view as AppCompatImageView)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUpcomingViewHolder {
        val itemView = parent.inflater().inflate(R.layout.list_item_movie_popular, parent, false)
        return MovieUpcomingViewHolder(itemView, clickListener)
    }
}

class MovieUpcomingViewHolder(
    itemView: View,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener
) : BaseViewHolder<ListItemMoviePopularBinding, MovieVO>(itemView) {
    override val binding: ListItemMoviePopularBinding by lazy {
        ListItemMoviePopularBinding.bind(itemView)
    }

    override fun bind(item: MovieVO) {
        val imageUrl = "https://image.tmdb.org/t/p/w500${item.posterPath}"
        Glide.with(itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_place_holder)
            .into(binding.ivMoviePoster)
        binding.tvMovieTitle.text = item.originalTitle
        if (item.isFavorite) {
            binding.cbFavorite.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_filled)
            )
        } else {
            binding.cbFavorite.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_border)
            )
        }
    }

    init {
        binding.cbFavorite.setOnClickListener {
            recyclerViewItemClickListener.onItemClick(it, bindingAdapterPosition)
        }
        binding.ivMoviePoster.setOnClickListener {
            recyclerViewItemClickListener.onItemClick(it, bindingAdapterPosition)
        }
    }
}
