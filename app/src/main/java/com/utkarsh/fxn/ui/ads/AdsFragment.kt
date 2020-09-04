package com.utkarsh.fxn.ui.ads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utkarsh.fxn.databinding.FragmentAdsBinding
import com.utkarsh.fxn.databinding.FragmentIteminfoBinding

class AdsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAdsBinding.inflate(inflater)




        return binding.root
    }
}