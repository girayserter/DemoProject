package com.example.demoproject.interfaces

import com.example.demoproject.models.MovieDetails
import com.example.demoproject.models.Trending
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiInterface {
    @GET("trending/movie/week")
    fun getTrendingMoviesWeek(@Query("api_key") apiKey:String):Call<Trending>

    @GET("trending/movie/day")
    fun getTrendingMoviesDay(@Query("api_key") apiKey:String):Call<Trending>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey:String,@Query("language") language:String):Call<Trending>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey:String,@Query("region") region: String):Call<Trending>

    @GET("movie/{movie_id}")
    fun getMovieDetailsById(@Path("movie_id") movieId:Int,@Query("api_key") apiKey:String):Call<MovieDetails>

    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey:String,@Query("query") query: String,@Query("include_adult") includeAdult: Boolean):Call<Trending>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendedMovies(@Path("movie_id") movieId:Int,@Query("api_key") apiKey:String):Call<Trending>
}