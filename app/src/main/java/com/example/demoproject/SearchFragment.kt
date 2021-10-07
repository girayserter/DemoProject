package com.example.demoproject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.adapters.MoviesAdapter
import com.example.demoproject.databinding.SearchFragmentBinding
import com.example.demoproject.interfaces.MovieOnClickInterface
import com.example.demoproject.models.Result
import com.example.demoproject.viewmodels.SearchViewModel


class SearchFragment : Fragment(), MovieOnClickInterface {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val binding : SearchFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.search_fragment,container,false)

        var adapterSearchResults = MoviesAdapter(this)
        binding.rcvSearchResults.layoutManager= GridLayoutManager(this.context,3)
        binding.rcvSearchResults.adapter=adapterSearchResults

        //On text changed on Search, get new movie results
        binding.txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    //Get searched movie results
                    viewModel.searchMovies(s.toString()).observe(viewLifecycleOwner,
                        { movieList ->
                            adapterSearchResults.addMovieList(movieList)
                            adapterSearchResults.notifyDataSetChanged()
                        })
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        return binding.root
    }

    /**
     * On recyclerview item click get movie_id and pass by navigation
     * Show related fragment
     */
    override fun onItemClick(result: Result?) {
        var action= result?.let {
            SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(
                it.id)
        }
        if (action != null) {
            findNavController().navigate(action)
        }
    }


}