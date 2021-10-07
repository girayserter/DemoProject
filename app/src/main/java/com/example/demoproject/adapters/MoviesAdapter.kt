package com.example.demoproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.R
import com.example.demoproject.databinding.ListItemMovieBinding
import com.example.demoproject.interfaces.MovieOnClickInterface
import com.example.demoproject.models.Result

class MoviesAdapter(var movieOnClickInterface: MovieOnClickInterface) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movieList : List<Result?>? = ArrayList()

    inner class ViewHolder(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.movieCardView.setOnClickListener {
                movieOnClickInterface.onItemClick(binding.movie)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_movie,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movie=movieList?.get(position)
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    fun addMovieList(movieList: List<Result?>?) {
        this.movieList = movieList
    }
}