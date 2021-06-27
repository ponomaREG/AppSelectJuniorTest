package com.appselect.junior.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.appselect.junior.R
import com.appselect.junior.databinding.ActivityMainBinding
import com.appselect.junior.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_JuniorTest)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val movieAdapter = MovieAdapter()
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