package com.ptut.appbase.core.recyclerview

import android.view.View

interface RecyclerViewItemClickListener {
  fun onItemClick(view: View, position: Int)
}

inline fun recyclerViewItemClickListener(
  crossinline onItemLongClick: ((@ParameterName("view") View, @ParameterName("position") Int) -> Unit) = { _, _ -> },
  crossinline onItemClick: ((@ParameterName("view") View, @ParameterName("position") Int) -> Unit) = { _, _ -> }
): RecyclerViewItemClickListener {
  return object : RecyclerViewItemClickListener {
    override fun onItemClick(view: View, position: Int) {
      onItemClick.invoke(view, position)
    }
  }
}

