package com.utkarsh.fxn.ui.mywatchlist

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding
import com.utkarsh.fxn.ui.searchresult.movie.SearchMovieViewModel
import com.utkarsh.fxn.util.hideKeyboard
import com.utkarsh.fxn.util.showKeyboard
import kotlinx.android.synthetic.main.fragment_mywatchlist.*


class MyWatchlistFragment : Fragment() {

    private lateinit var myWatchlistViewModel : MyWatchlistViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMywatchlistBinding.inflate(inflater)


        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->

            if (v.text.trim().isNotEmpty()) {
                v.findNavController().navigate(
                    MyWatchlistFragmentDirections
                        .actionMyWatchlistFragmentToSearchResultFragment(v.text.trim().toString())
                )

                hideKeyboard()
            } else {
                Toast.makeText(context, "Aja tujhe sundaas dikhata hu!", Toast.LENGTH_SHORT).show()
            }

            v.text = ""

            false
        }

        binding.lifecycleOwner = this

        myWatchlistViewModel = ViewModelProviders.of(this).get(MyWatchlistViewModel::class.java)

        binding.mwlVM = myWatchlistViewModel

        myWatchlistViewModel.fabClicked.observe(viewLifecycleOwner, Observer {
            if(it==true){

                binding.mwlMotionLayout.transitionToEnd()

                binding.searchEditText.requestFocus()
                binding.searchEditText.isFocusableInTouchMode = true

                showKeyboard( binding.searchEditText)
                Log.v("utk", "On Keyboard Button click event!")
            }
            else
            {
                binding.mwlMotionLayout.transitionToStart()
                hideKeyboard()
            }

        })





        //open item fragment - temporary
        binding.itemopen.setOnClickListener{
            findNavController().navigate(MyWatchlistFragmentDirections.actionMyWatchlistFragmentToItemInfoFragment())
        }





        return binding.root
    }


//    fun fabClick(v:View)
//    {
//
//            search_editText.requestFocus()
//            search_editText.isFocusableInTouchMode = true
//
//            showKeyboard(search_editText)
//            Log.v("utk", "On Keyboard Button click event!")
//
//    }
}