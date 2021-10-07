package com.example.demoproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.demoproject.adapters.ListViewPagerAdapter
import com.example.demoproject.databinding.MovieDetailsFragmentBinding
import com.example.demoproject.databinding.MyMoviesFragmentBinding
import com.example.demoproject.viewmodels.MovieDetailsViewModel
import com.example.demoproject.viewmodels.MyMoviesViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MyMoviesFragment : Fragment() {

    private lateinit var viewModel: MyMoviesViewModel
    private lateinit var listViewPagerAdapter: ListViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : MyMoviesFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.my_movies_fragment,container,false)
        viewModel = ViewModelProvider(this).get(MyMoviesViewModel::class.java)

        listViewPagerAdapter= ListViewPagerAdapter(this)
        binding.pager.adapter=listViewPagerAdapter

        //Tab layout tab names
        TabLayoutMediator(binding.tabLayout,binding.pager)
        { tab,position ->
            when(position){
                0 -> {
                    tab.text="Watched"
                }

                1-> {
                    tab.text="Watch List"
                }
            }
        }.attach()

        return binding.root
    }

}