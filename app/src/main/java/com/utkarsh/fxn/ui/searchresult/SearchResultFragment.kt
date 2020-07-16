package com.utkarsh.fxn.ui.searchresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding
import com.utkarsh.fxn.databinding.FragmentSearchresultBinding
import kotlinx.android.synthetic.main.fragment_searchresult.*

class SearchResultFragment : Fragment() {

    public var searchTitle: String = ""

//    private lateinit var searchResultViewModel : SearchResultViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchresultBinding.inflate(inflater)

        val args =SearchResultFragmentArgs.fromBundle(requireArguments())
    searchTitle = args.searchString

//
//        binding.textView.text=args.searchString
//
//        binding.lifecycleOwner = this
//
//
//
//        val viewModelFactory =SearchResultViewModelFactory(args.searchString)
//
//        searchResultViewModel =ViewModelProviders.of(this,viewModelFactory).get(SearchResultViewModel::class.java)
//
//        binding.searchVM=searchResultViewModel
//
//        binding.resultList.adapter=SearchListAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mViewPagerAdapter = SearchViewPagerAdapter(this,searchTitle)
        view_pager.adapter= mViewPagerAdapter
        view_pager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(tabs, view_pager) { tab, position ->
            if (position==0)
                tab.text="Movies"
            else
                tab.text="Tv Shows"


        }.attach()
    }
}

