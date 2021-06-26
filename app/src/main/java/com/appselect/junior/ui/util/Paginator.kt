package com.appselect.junior.ui.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appselect.junior.ui.adapter.MovieAdapter

class Paginator constructor(
        private val recyclerView: RecyclerView,
        private val onLoadMoreItem: (Int) -> Unit,
        private val isLoading: () -> Boolean
): RecyclerView.OnScrollListener() {
    private val threshold = 5
    private var currentPage = 0

    init {
        recyclerView.addOnScrollListener(this)
        val adapter = recyclerView.adapter
        if(adapter is MovieAdapter){
            adapter.setOnClearListener(object: MovieAdapter.OnClearListener(){
                override fun onClear() {
                    currentPage = 0
                }
            })
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(isLoading()) return

        val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        if((visibleItemCount + lastVisibleItemPosition + threshold) > totalItemCount){
            onLoadMoreItem(++currentPage)
        }
    }
}