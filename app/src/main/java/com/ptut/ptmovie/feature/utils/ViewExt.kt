package com.ptut.ptmovie.feature.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.initialize() {
    hasFixedSize()
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

fun RecyclerView.initializeHorizontal() {
    hasFixedSize()
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.initializeGrid(column: Int) {
    hasFixedSize()
    layoutManager = GridLayoutManager(context, column)
}
