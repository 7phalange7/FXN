package com.utkarsh.fxn.ui.mywatchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding

class MyWatchlistFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMywatchlistBinding.inflate(inflater)


        binding.searchEditText.setOnEditorActionListener{ v, actionId, event ->

            if(v.text.trim().isNotEmpty())
            {
                v.findNavController().navigate(MyWatchlistFragmentDirections
                    .actionMyWatchlistFragmentToSearchResultFragment(v.text.trim().toString()))

               // hideKeyboard()
            }
            else
            {
                Toast.makeText(context,"Aja tujhe sundaas dikhata hu!", Toast.LENGTH_SHORT).show()
            }

            v.text=""

            false
        }

        return binding.root
    }
}