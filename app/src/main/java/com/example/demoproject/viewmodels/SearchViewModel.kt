package com.example.demoproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoproject.models.MovieDetails
import com.example.demoproject.models.Result
import com.example.demoproject.repositories.MoviesRepo

class SearchViewModel : ViewModel() {
    val moviesRepo= MoviesRepo()


    fun searchMovies(string : String): MutableLiveData<List<Result?>?> {
        moviesRepo.searchMovie(string)
        return moviesRepo.searchResultsLiveData
    }
}