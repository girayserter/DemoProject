package com.example.demoproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.R
import com.example.demoproject.databinding.ListItemWatchedListBinding
import com.example.demoproject.interfaces.WatchedListClickInterface
import com.example.demoproject.models.WatchedMovie

class WatchedListAdapter(var movieOnClickInterface: WatchedListClickInterface) : RecyclerView.Adapter<WatchedListAdapter.ViewHolder>() {

    private var movieList : List<WatchedMovie?>? = ArrayList()

    inner class ViewHolder(val binding: ListItemWatchedListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.movieCardView.setOnClickListener {
                movieOnClickInterface.onItemClick(binding.movie)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemWatchedListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_watched_list,
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

    fun addMovieList(movieList: List<WatchedMovie?>?) {
        this.movieList = movieList
    }
}
