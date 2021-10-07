package com.example.demoproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoproject.models.Result
import com.example.demoproject.models.Trending
import com.example.demoproject.repositories.MoviesRepo

class HomaPageViewModel : ViewModel() {
    val moviesRepo=MoviesRepo()


    fun getTrendingMoviesWeek(): LiveData<List<Result?>?> {
        return moviesRepo.trendingMoviesWeekLiveData
    }

    fun getTrendingMoviesDay(): LiveData<List<Result?>?> {
        return moviesRepo.trendingMoviesDayLiveData
    }

    fun getTopRatedMovies(): LiveData<List<Result?>?> {
        return moviesRepo.topRatedMoviesLiveData
    }

    fun getUpcomingMovies(region:String): LiveData<List<Result?>?> {
        moviesRepo.getUpcomingMovies(region)
        return moviesRepo.upcomingMoviesLiveData
    }

}