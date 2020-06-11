package com.utkarsh.fxn.ui.iteminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utkarsh.fxn.databinding.FragmentIteminfoBinding
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding

class ItemInfoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentIteminfoBinding.inflate(inflater)

        return binding.root
    }
}