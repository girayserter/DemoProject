package com.example.demoproject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.adapters.WatchedListAdapter
import com.example.demoproject.databinding.WatchedMoviesListFragmentBinding
import com.example.demoproject.interfaces.WatchedListClickInterface
import com.example.demoproject.models.WatchedMovie
import com.example.demoproject.viewmodels.WatchedMoviesListViewModel

class WatchedMoviesListFragment : Fragment(),WatchedListClickInterface {

    private lateinit var viewModel: WatchedMoviesListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : WatchedMoviesListFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.watched_movies_list_fragment,container,false)
        viewModel = ViewModelProvider(this).get(WatchedMoviesListViewModel::class.java)

        binding.rcvWatchedList.layoutManager= GridLayoutManager(this.context,3)
        var adapter= WatchedListAdapter(this)
        binding.rcvWatchedList.adapter=adapter

        arguments?.takeIf { it.containsKey("watch_state") }?.apply {

            when (getString("watch_state")){
                "watched" ->{
                    viewModel.watchedMovies.observe(viewLifecycleOwner)
                    { movieList ->
                        adapter.addMovieList(movieList)
                        adapter.notifyDataSetChanged()
                    }
                }

                "unwatched" ->{
                    viewModel.unwatchedMovies.observe(viewLifecycleOwner)
                    { movieList ->
                        adapter.addMovieList(movieList)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

        return binding.root
    }

    /**
     * On recyclerview item click get movie_id and pass by navigation
     * Show related fragment
     */
    override fun onItemClick(movie: WatchedMovie?) {
        var action=movie?.let {
            MyMoviesFragmentDirections.actionMyMoviesFragmentToMovieDetailsFragment(it.movieId)
        }
        if (action!=null){
            findNavController().navigate(action)
        }
    }


}