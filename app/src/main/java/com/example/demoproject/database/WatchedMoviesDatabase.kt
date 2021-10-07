package com.example.demoproject.database

import android.content.Context
import android.telecom.Call
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demoproject.models.WatchedMovie

@Database(entities = [WatchedMovie::class], version = 1)
abstract class WatchedMoviesDatabase:RoomDatabase() {

    abstract fun watchedMovieDao(): WatchedMovieDao

    companion object{
        @Volatile
        private var instance:WatchedMoviesDatabase?= null

        @Synchronized
        fun getDatabase(context: Context):WatchedMoviesDatabase{
            if (instance==null){
                instance=Room.databaseBuilder(
                    context,
                    WatchedMoviesDatabase::class.java,
                    "WatchedMoviesDB"
                ).build()
            }
            return instance!!
        }

    }
}