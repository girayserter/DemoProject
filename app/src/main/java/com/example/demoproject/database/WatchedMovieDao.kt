package com.example.demoproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demoproject.models.WatchedMovie

@Dao
interface WatchedMovieDao {
    @Query("SELECT * FROM watched_movies WHERE is_watched=1")
    fun getWatchedMovies() : LiveData<List<WatchedMovie>>

    @Query("SELECT * FROM watched_movies WHERE is_watched=0")
    fun getUnwatchedMovies() : LiveData<List<WatchedMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(vararg movie: WatchedMovie)

    @Delete
    fun delete(movie: WatchedMovie)

    @Query("SELECT is_watched FROM watched_movies WHERE movieId LIKE :movieId")
    fun isMovieWatched(movieId: Int): LiveData<Boolean>
}