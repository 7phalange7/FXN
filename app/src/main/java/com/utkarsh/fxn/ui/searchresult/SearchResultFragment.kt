package com.utkarsh.fxn.ui.searchresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding
import com.utkarsh.fxn.databinding.FragmentSearchresultBinding

class SearchResultFragment : Fragment() {

    private lateinit var searchResultViewModel : SearchResultViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchresultBinding.inflate(inflater)

        val args =SearchResultFragmentArgs.fromBundle(requireArguments())

        binding.textView.text=args.searchString

        binding.lifecycleOwner = this



        val viewModelFactory =SearchResultViewModelFactory(args.searchString)

       searchResultViewModel =ViewModelProviders.of(this,viewModelFactory).get(SearchResultViewModel::class.java)

        binding.searchVM=searchResultViewModel

        binding.resultList.adapter=SearchListAdapter()

        return binding.root
    }
}