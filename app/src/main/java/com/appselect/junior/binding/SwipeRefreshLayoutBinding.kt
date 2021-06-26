package com.appselect.junior.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isLoading")
fun bindLoadingIndicator(view: SwipeRefreshLayout, isLoading: Boolean){
    view.isRefreshing = isLoading
}

@BindingAdapter("isEnabled")
fun bindEnableOfRefreshing(view: SwipeRefreshLayout, isEnabled: Boolean){
    view.isEnabled = isEnabled
}