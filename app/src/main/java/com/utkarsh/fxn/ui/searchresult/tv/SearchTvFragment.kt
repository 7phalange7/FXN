package com.utkarsh.fxn.ui.searchresult.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.utkarsh.fxn.databinding.FragmentSearchresultBinding
import com.utkarsh.fxn.databinding.FragmentSearchtvBinding
import com.utkarsh.fxn.ui.searchresult.SearchListAdapter
import com.utkarsh.fxn.ui.searchresult.SearchResultFragmentArgs


class SearchTvFragment(private var searchtitle : String)  : Fragment() {

    private lateinit var searchtvViewModel : SearchTvViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchtvBinding.inflate(inflater)

//        val args = SearchResultFragmentArgs.fromBundle(requireArguments())
//
//        binding.textView.text=args.searchString

        binding.lifecycleOwner = this



        val viewModelFactory = SearchTvViewModelFactory(searchtitle)

        searchtvViewModel =
            ViewModelProviders.of(this,viewModelFactory).get(SearchTvViewModel::class.java)

        binding.searchtvVM=searchtvViewModel

        binding.resultList.adapter= SearchListAdapter(SearchListAdapter.MovieClickListener {
                id ->
            //Toast.makeText(context, "${id}", Toast.LENGTH_LONG).show()

            findNavController().navigate(SearchTvFragmentDirections.actionGlobalItemTvFragment(id))
        })



        return binding.root
    }
}