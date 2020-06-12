package com.utkarsh.fxn.ui.searchresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding
import com.utkarsh.fxn.databinding.FragmentSearchresultBinding

class SearchResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchresultBinding.inflate(inflater)

        val args =SearchResultFragmentArgs.fromBundle(requireArguments())

        binding.textView.text=args.searchString

        return binding.root
    }
}