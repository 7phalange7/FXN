package com.utkarsh.fxn.ui.mywatchlist

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.utkarsh.fxn.R
import com.utkarsh.fxn.data.firebase.TvFirebase
import com.utkarsh.fxn.databinding.FragmentMywatchlistBinding
import com.utkarsh.fxn.ui.searchresult.tv.SearchTvFragmentDirections
import com.utkarsh.fxn.util.hideKeyboard
import com.utkarsh.fxn.util.showKeyboard
import kotlinx.android.synthetic.main.fragment_mywatchlist.*


class MyWatchlistFragment : Fragment() {

    private lateinit var myWatchlistViewModel : MyWatchlistViewModel

    //firebase
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var dbReference : DatabaseReference
    private lateinit var firebaseAdapter : FirebaseRVAdapter
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMywatchlistBinding.inflate(inflater)


        //(activity as AppCompatActivity).supportActionBar?.show()
      //  (activity as AppCompatActivity).setSupportActionBar(binding.actionBarMywatchlist)

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




        //firebase database stuff



        auth = Firebase.auth
        val uid = auth.uid
        firebaseDatabase= Firebase.database
        dbReference = firebaseDatabase.getReference("users/$uid")

        val query = dbReference.child("tv")


        var optionss: FirebaseRecyclerOptions<TvFirebase> = FirebaseRecyclerOptions.Builder<TvFirebase>()
            .setQuery(query, TvFirebase::class.java)
            .build()


        firebaseAdapter = FirebaseRVAdapter(FirebaseRVAdapter.ItemClickListener {
                id ->


            findNavController().navigate(MyWatchlistFragmentDirections.actionGlobalItemTvFragment(id!!))

        }, optionss)

        binding.firebaseRecyclerView.adapter=firebaseAdapter



        return binding.root
    }



    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun onStart() {
        super.onStart()
        firebaseAdapter.startListening()

    }

    override fun onStop() {
        super.onStop()
        firebaseAdapter.stopListening()

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