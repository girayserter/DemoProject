package com.example.demoproject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject.adapters.MoviesAdapter
import com.example.demoproject.database.WatchedMoviesDatabase
import com.example.demoproject.databinding.HomaPageFragmentBinding
import com.example.demoproject.databinding.MovieDetailsFragmentBinding
import com.example.demoproject.interfaces.MovieOnClickInterface
import com.example.demoproject.models.Result
import com.example.demoproject.models.WatchedMovie
import com.example.demoproject.viewmodels.HomaPageViewModel
import com.example.demoproject.viewmodels.MovieDetailsViewModel
import kotlinx.coroutines.runBlocking

class MovieDetailsFragment : Fragment(), MovieOnClickInterface {

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args:MovieDetailsFragmentArgs by navArgs() // arguments from previous fragment
        val binding : MovieDetailsFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.movie_details_fragment,container,false)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        //Get movie details of related movie_id and set binding variable movie
        viewModel.getMovieDetails(args.movieId).observe(viewLifecycleOwner,
            {
                movieDetails ->
                    binding.movie=movieDetails
            })

        //Setting adapter of recommended movies recyclerview
        var adapterRecommended = MoviesAdapter(this)
        binding.rcvRecommended.layoutManager= LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        binding.rcvRecommended.adapter=adapterRecommended

        //Getting Recommended Movies
        viewModel.getRecommendedMovies(args.movieId).observe(viewLifecycleOwner
        ) { movieList ->
            adapterRecommended.addMovieList(movieList)
            adapterRecommended.notifyDataSetChanged()
        }

        /**
         * Checks if movie's watched status is 'watched' or 'will watch' in local Room DB
         * if so change the corresponding button text
         */
        runBlocking {
            viewModel.getMovieWatchedStatus(args.movieId).observe(viewLifecycleOwner)
            { movieStatus ->
                when (movieStatus){
                    true -> {
                        binding.btnWatched.text="WATCHEEEDD"
                        binding.btnWillWatch.text="Will Watch"
                    }
                    false -> {
                        binding.btnWillWatch.text="UNWATCHEEDDD"
                        binding.btnWatched.text="Watched"
                    }
                }

            }
        }

        //On watched button click add movie to database watch list with 'watched' value
        binding.btnWatched.setOnClickListener {
            viewModel.addMovieToList(true)
        }

        //On will watch button click add movie to database watch list with 'will watch' value
        binding.btnWillWatch.setOnClickListener {
            viewModel.addMovieToList(false)
        }

        return binding.root
    }

    /**
     * On recyclerview item click get movie_id and pass by navigation
     * Show related fragment
     */
    override fun onItemClick(result: Result?) {
        var action= result?.let {
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(
                it.id)
        }
        if (action != null) {
            findNavController().navigate(action)
        }
    }

}