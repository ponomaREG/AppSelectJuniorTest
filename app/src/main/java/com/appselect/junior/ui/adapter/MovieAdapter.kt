package com.appselect.junior.ui.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appselect.junior.R
import com.appselect.junior.databinding.ItemMovieBinding
import com.appselect.junior.databinding.ItemMovieDescriptionBinding
import com.appselect.junior.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.Holder>() {

    private val _items: MutableList<Movie> = mutableListOf()


    fun addItems(items: List<Movie>) {
        val positionStart = _items.size
        _items.addAll(items)
        notifyItemRangeInserted(positionStart, items.size)
    }

    fun clearAll() {
        _items.clear()
        notifyDataSetChanged()
    }


    class Holder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            background.movie = _items[position]
            foreground.movie = _items[position]
        }
    }

    override fun getItemCount(): Int = _items.size

}