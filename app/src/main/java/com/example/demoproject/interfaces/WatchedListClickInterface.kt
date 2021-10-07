package com.example.demoproject.interfaces

import com.example.demoproject.models.Result
import com.example.demoproject.models.WatchedMovie

interface WatchedListClickInterface {

    fun onItemClick(movie: WatchedMovie?)

}