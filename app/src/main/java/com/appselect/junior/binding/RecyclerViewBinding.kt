package com.appselect.junior.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.appselect.junior.model.Movie
import com.appselect.junior.ui.adapter.MovieAdapter
import com.appselect.junior.ui.main.MainViewModel
import com.appselect.junior.ui.util.Paginator

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter:RecyclerView.Adapter<*>){
    view.adapter = adapter
}

@BindingAdapter("moviesList")
fun bindMoviesList(view: RecyclerView, movies: List<Movie>?){
    if((view.adapter != null) and (view.adapter is MovieAdapter) and (movies.isNullOrEmpty().not())){
        (view.adapter as MovieAdapter).addItems(movies!!)
    }
}

@BindingAdapter("divider")
fun bindDivider(view: RecyclerView, dividerItemDecoration: RecyclerView.ItemDecoration){
    view.addItemDecoration(dividerItemDecoration)
}

@BindingAdapter("paginator")
fun bindPaginator(view: RecyclerView, viewModel: MainViewModel){
    Paginator(
            recyclerView = view,
            onLoadMoreItem = {viewModel.loadMovies(it)},
            isLoading = {viewModel.isLoading.get()}
    )
}