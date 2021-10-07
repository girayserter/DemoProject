package com.example.demoproject.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoproject.database.WatchedMoviesDatabase
import com.example.demoproject.models.MovieDetails
import com.example.demoproject.models.Result
import com.example.demoproject.models.WatchedMovie
import com.example.demoproject.repositories.DatabaseRepo
import com.example.demoproject.repositories.MoviesRepo
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepo= MoviesRepo()
    private val databaseRepo= DatabaseRepo(application)
    lateinit var watched: LiveData<Boolean>
    private val movieDetails= MutableLiveData<MovieDetails?>()


    fun getMovieDetails(id:Int): MutableLiveData<MovieDetails?> {
        moviesRepo.getMovieDetailsById(id)
        movieDetails.value=moviesRepo.movieDetailsLiveData.value
        return moviesRepo.movieDetailsLiveData
    }

    fun getRecommendedMovies(id: Int): LiveData<List<Result?>?> {
        moviesRepo.getRecommendedMovies(id)
        return moviesRepo.recommendedMoviesLiveData
    }

    fun getMovieWatchedStatus(id:Int): LiveData<Boolean> {
        return databaseRepo.isMovieWatched(id)
    }

    fun addMovieToList(isWatched: Boolean)= viewModelScope.launch{
        val movie=WatchedMovie(
            moviesRepo.movieDetailsLiveData.value?.id!!,
            isWatched,
            moviesRepo.movieDetailsLiveData.value?.original_title!!,
            moviesRepo.movieDetailsLiveData.value?.poster_path!!,
            )

        databaseRepo.addMovie(movie)
    }

}