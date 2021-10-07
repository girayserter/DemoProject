package com.example.demoproject.repositories

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoproject.database.WatchedMovieDao
import com.example.demoproject.database.WatchedMoviesDatabase
import com.example.demoproject.models.WatchedMovie

class DatabaseRepo(application: Application) {
    private val database=WatchedMoviesDatabase.getDatabase(application)

    val watchedMovieDao=database.watchedMovieDao()
    var watchedMoviesList: LiveData<List<WatchedMovie>> = watchedMovieDao.getWatchedMovies()
    var unwatchedMoviesList: LiveData<List<WatchedMovie>> = watchedMovieDao.getUnwatchedMovies()


    suspend fun addMovie(movie: WatchedMovie){
        watchedMovieDao.addMovie(movie)
    }


    fun isMovieWatched(movieId: Int): LiveData<Boolean>{
        return watchedMovieDao.isMovieWatched(movieId)
    }




}