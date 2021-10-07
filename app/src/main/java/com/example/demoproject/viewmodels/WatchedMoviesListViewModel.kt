package com.example.demoproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.demoproject.repositories.DatabaseRepo

class WatchedMoviesListViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseRepo= DatabaseRepo(application)
    val watchedMovies= databaseRepo.watchedMoviesList
    val unwatchedMovies= databaseRepo.unwatchedMoviesList


}