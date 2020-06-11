package com.utkarsh.fxn.ui.mywatchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding

class MyWatchlistFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMywatchlistBinding.inflate(inflater)

        return binding.root
    }
}