package com.appselect.junior.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.appselect.junior.R
import com.appselect.junior.databinding.ActivityMainBinding
import com.appselect.junior.ui.adapter.MovieAdapter
import com.appselect.junior.ui.util.Paginator
import com.appselect.junior.ui.util.SwipeTouchHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val movieAdapter = MovieAdapter()
        val swipeTouchHelperCallBack = SwipeTouchHelper()
        val itemTouchHelper = ItemTouchHelper(swipeTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rv)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            adapter = movieAdapter
            onSwipeRefresh.setOnRefreshListener {
                movieAdapter.clearAll()
                viewModel.loadMovies(0)
            }
        }
        viewModel.loadMovies(0)
    }
}