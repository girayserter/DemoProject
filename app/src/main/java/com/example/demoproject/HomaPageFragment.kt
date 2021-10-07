package com.example.demoproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject.adapters.MoviesAdapter
import com.example.demoproject.databinding.HomaPageFragmentBinding
import com.example.demoproject.interfaces.MovieOnClickInterface
import com.example.demoproject.models.Result
import com.example.demoproject.viewmodels.HomaPageViewModel
import com.example.mymodule.Location

class HomaPageFragment : Fragment(),MovieOnClickInterface{

    private lateinit var viewModel: HomaPageViewModel
    lateinit var countryCode:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        viewModel = ViewModelProvider(this).get(HomaPageViewModel::class.java)
        val binding : HomaPageFragmentBinding=DataBindingUtil.inflate(inflater,R.layout.homa_page_fragment,container,false)

        var adapterTrendingWeek = MoviesAdapter(this)
        var adapterTrendingDay = MoviesAdapter(this)
        var adapterTopRated = MoviesAdapter(this)
        var adapterUpcoming = MoviesAdapter(this)

        //Trending Movies Of Week Recyclerview
        binding.rcvTrendingWeek.layoutManager=LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        binding.rcvTrendingWeek.adapter=adapterTrendingWeek

        //Trending Movies Of Day Recyclerview
        binding.rcvTrendingDay.layoutManager=LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        binding.rcvTrendingDay.adapter=adapterTrendingDay

        //Top Rated Movies Recyclerview
        binding.rcvTopRated.layoutManager=LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        binding.rcvTopRated.adapter=adapterTopRated

        //Upcoming Movies Recyclerview
        binding.rcvUpcoming.layoutManager=LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        binding.rcvUpcoming.adapter=adapterUpcoming

        /**
            Initialize Location class from mymodule module
            On create, Location class checks if location permission available
            if not, asks for permission. On permission granted, checks the
            last known location
         */
        val locationModule= Location(this.activity as AppCompatActivity)
        //Observing one of the parameters of location to know when it is assigned
        locationModule.lng.observe(viewLifecycleOwner){
            countryCode=locationModule.getCountryCode()
            loadMovies(adapterTrendingWeek,adapterTrendingDay,adapterTopRated,adapterUpcoming,countryCode)
        }


        return binding.root
    }

    /**
     * Gets Adapters and observing data changes on viewmodel
     * Refreshes recyclerviews
     */
    fun loadMovies(
        adapterTrendingWeek: MoviesAdapter,
        adapterTrendingDay: MoviesAdapter,
        adapterTopRated: MoviesAdapter,
        adapterUpcoming: MoviesAdapter,
        region:String
    ){
        viewModel.getTrendingMoviesWeek().observe(viewLifecycleOwner,
            { movieList ->
                adapterTrendingWeek.addMovieList(movieList)
                adapterTrendingWeek.notifyDataSetChanged()
            })


        viewModel.getTrendingMoviesDay().observe(viewLifecycleOwner,
            { movieList ->
                adapterTrendingDay.addMovieList(movieList)
                adapterTrendingDay.notifyDataSetChanged()
            })

        viewModel.getTopRatedMovies().observe(viewLifecycleOwner,
            { movieList ->
                adapterTopRated.addMovieList(movieList)
                adapterTopRated.notifyDataSetChanged()
            })

        viewModel.getUpcomingMovies(region).observe(viewLifecycleOwner,
            { movieList ->
                adapterUpcoming.addMovieList(movieList)
                adapterUpcoming.notifyDataSetChanged()
            })
    }

    /**
     * On recyclerview item click get movie_id and pass by navigation
     * Show related fragment
     */
    override fun onItemClick(result: Result?) {
        var action= result?.let {
            HomaPageFragmentDirections.actionHomaPageFragmentToMovieDetailsFragment(
                it.id)
        }
        if (action != null) {
            findNavController().navigate(action)
        }
    }


}