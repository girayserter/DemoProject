package com.example.demoproject.models


data class Result (
    val adult:Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val poster_path: String,
    val video: Boolean,
    val vote_average: Double,
    val id: Int,
    val overview: String,
    val release_date: String,
    val vote_count: Int,
    val title: String,
    val popularity: Double,
    val media_type: String
)