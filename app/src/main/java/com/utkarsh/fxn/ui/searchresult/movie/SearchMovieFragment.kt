package com.utkarsh.fxn.ui.searchresult.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.utkarsh.fxn.databinding.FragmentSearchmovieBinding
import com.utkarsh.fxn.databinding.FragmentSearchresultBinding
import com.utkarsh.fxn.ui.searchresult.SearchListAdapter
import com.utkarsh.fxn.ui.searchresult.SearchResultFragmentArgs

class SearchMovieFragment(private var searchtitle : String)  : Fragment() {

    private lateinit var searchmovieViewModel : SearchMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchmovieBinding.inflate(inflater)

//        val args = SearchResultFragmentArgs.fromBundle(requireArguments())
//
//       binding.textView.text=args.searchString

        binding.lifecycleOwner = this



        val viewModelFactory = SearchMovieViewModelFactory(searchtitle)

        searchmovieViewModel =
            ViewModelProviders.of(this,viewModelFactory).get(SearchMovieViewModel::class.java)

        binding.searchmovieVM=searchmovieViewModel

        binding.resultList.adapter= SearchListAdapter(SearchListAdapter.MovieClickListener {
           id ->
            Toast.makeText(context, "$id", Toast.LENGTH_LONG).show()

            findNavController().navigate(SearchMovieFragmentDirections.actionGlobalItemMovieFragment(id))
        })

        return binding.root
    }
}