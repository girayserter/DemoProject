package com.example.demoproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched_movies")
data class WatchedMovie (

    @PrimaryKey
    val movieId: Int,

    @ColumnInfo(name="is_watched")
    val isWatched: Boolean,

    @ColumnInfo(name="title")
    val title: String,

    @ColumnInfo(name="poster_path")
    val posterPath: String

)
