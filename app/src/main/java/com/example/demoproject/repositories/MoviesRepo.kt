package com.example.demoproject.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demoproject.interfaces.TMDBApiInterface
import com.example.demoproject.models.MovieDetails
import com.example.demoproject.models.Result
import com.example.demoproject.models.Trending
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepo {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val API_KEY= "0e9a247c7707310f60710a1f75445def"
    val trendingMoviesWeekLiveData=MutableLiveData<List<Result?>?>()
    val trendingMoviesDayLiveData=MutableLiveData<List<Result?>?>()
    val topRatedMoviesLiveData=MutableLiveData<List<Result?>?>()
    val upcomingMoviesLiveData=MutableLiveData<List<Result?>?>()
    val movieDetailsLiveData=MutableLiveData<MovieDetails?>()
    val searchResultsLiveData=MutableLiveData<List<Result?>?>()
    val recommendedMoviesLiveData=MutableLiveData<List<Result?>?>()

    init {
        getTrendingMoviesWeek()
        getTrendingMoviesDay()
        getTopRatedMovies()
    }

    fun getTrendingMoviesWeek() {
        getApiService().getTrendingMoviesWeek(API_KEY).enqueue(object : Callback<Trending> {
            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                trendingMoviesWeekLiveData.value= response.body()?.results
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                trendingMoviesWeekLiveData.value=null
            }

        })

    }

    fun getTrendingMoviesDay() {
        getApiService().getTrendingMoviesDay(API_KEY).enqueue(object : Callback<Trending> {
            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                trendingMoviesDayLiveData.value= response.body()?.results
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                trendingMoviesDayLiveData.value=null
            }

        })

    }

    fun getTopRatedMovies(){
        getApiService().getTopRatedMovies(API_KEY,"en-US").enqueue(object : Callback<Trending> {
            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                topRatedMoviesLiveData.value= response.body()?.results
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                topRatedMoviesLiveData.value=null
            }

        })

    }

    fun getUpcomingMovies(region:String){
        getApiService().getUpcomingMovies(API_KEY,region).enqueue(object : Callback<Trending> {

            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                upcomingMoviesLiveData.value= response.body()?.results
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                upcomingMoviesLiveData.value=null
            }

        })
    }

    fun getMovieDetailsById(id: Int){
        getApiService().getMovieDetailsById(id,API_KEY).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                movieDetailsLiveData.value= response.body()
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                movieDetailsLiveData.value=null
            }

        })

    }

    fun searchMovie(string: String){
        getApiService().searchMovie(API_KEY,string,false).enqueue(object : Callback<Trending> {
            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                searchResultsLiveData.value= response.body()?.results
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                searchResultsLiveData.value=null
            }

        })
    }

    fun getRecommendedMovies(id: Int){
        getApiService().getRecommendedMovies(id,API_KEY).enqueue(object : Callback<Trending> {
            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                recommendedMoviesLiveData.value= response.body()?.results
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                recommendedMoviesLiveData.value=null
            }

        })

    }

    fun getApiService():TMDBApiInterface{
        val retrofitBuilder= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitBuilder.create(TMDBApiInterface::class.java)
    }
}