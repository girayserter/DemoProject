package com.example.demoproject.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demoproject.WatchedMoviesListFragment
import com.example.demoproject.database.WatchedMovieDao

class ListViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val ARG_OBJECT = "watch_state"
    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment {
        val fragment=WatchedMoviesListFragment()
        fragment.arguments= Bundle().apply {
            when(position){
                0-> putString(ARG_OBJECT, "watched")
                1-> putString(ARG_OBJECT, "unwatched")
            }
        }
        return fragment
    }
}