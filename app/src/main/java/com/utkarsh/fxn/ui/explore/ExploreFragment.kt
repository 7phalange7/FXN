package com.utkarsh.fxn.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utkarsh.fxn.databinding.FragmentExploreBinding
import com.utkarsh.fxn.databinding.FragmentIteminfoBinding


class ExploreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExploreBinding.inflate(inflater)




        return binding.root
    }
}