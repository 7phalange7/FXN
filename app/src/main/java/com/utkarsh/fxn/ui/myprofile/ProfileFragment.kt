package com.utkarsh.fxn.ui.myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utkarsh.fxn.databinding.FragmentIteminfoBinding
import com.utkarsh.fxn.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater)




        return binding.root
    }
}