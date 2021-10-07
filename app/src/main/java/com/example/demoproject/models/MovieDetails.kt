package com.example.demoproject.models

data class MovieDetails (
    val backdrop_path: String?,
    val original_title: String,
    val poster_path: String,
    val vote_average: Double,
    val id: Int,
    val overview: String?,
    val release_date: String,
    val genres: List<Genre>
){
    fun getGenre():String{
        var genre=""
        for (i in genres){
            genre+=i.name+","
        }
        genre=genre.dropLast(1)
        return genre
    }
}