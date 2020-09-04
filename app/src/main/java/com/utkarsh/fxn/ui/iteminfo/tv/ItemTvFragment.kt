package com.utkarsh.fxn.ui.iteminfo.tv

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.utkarsh.fxn.data.firebase.TvFirebase
import com.utkarsh.fxn.databinding.FragmentItemTvBinding
import com.utkarsh.fxn.ui.searchresult.SearchListAdapter
import com.utkarsh.fxn.ui.searchresult.tv.SearchTvFragmentDirections


class ItemTvFragment : Fragment() {

    private lateinit var itemTvViewModel: ItemTvViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemTvBinding.inflate(inflater)


        //(activity as AppCompatActivity).supportActionBar?.hide()



        binding.lifecycleOwner = this

        val args = ItemTvFragmentArgs.fromBundle(requireArguments())

        val id = args.itemiId
        val itemtvViewmodelFactory = ItemTvViewModelFactory(id)

        itemTvViewModel =
            ViewModelProviders.of(this, itemtvViewmodelFactory).get(ItemTvViewModel::class.java)

        binding.itemTvViewModel = itemTvViewModel



        var toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)

        binding.imdbLogo.setOnClickListener{
            toast.cancel()
            toast = Toast.makeText(context, "IMDb Rating", Toast.LENGTH_SHORT)
            toast.show()
        }
        binding.seasonsLogo.setOnClickListener{
            toast.cancel()
            toast = Toast.makeText(context, "Number Of Seasons", Toast.LENGTH_SHORT)
            toast.show()
        }
        binding.episodesLogo.setOnClickListener{
            toast.cancel()
            toast = Toast.makeText(context, "Total Episodes", Toast.LENGTH_SHORT)
            toast.show()
        }
        binding.runtimeLogo.setOnClickListener{
            toast.cancel()
            toast = Toast.makeText(context, "Episode Runtime (minutes)", Toast.LENGTH_SHORT)
            toast.show()
        }

        binding.genreRecyclerView.adapter=GenreListAdapter()

        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.flexWrap=FlexWrap.WRAP
        binding.genreRecyclerView.layoutManager = layoutManager

        binding.similarRecyclerView.adapter=SearchListAdapter(SearchListAdapter.MovieClickListener {
                id ->
            Toast.makeText(context, "Works??", Toast.LENGTH_LONG).show()

            findNavController().navigate(SearchTvFragmentDirections.actionGlobalItemTvFragment(id))

        })


//        //find why this not working !!!!!!!!
//        itemTvViewModel.iconClicked.observe(viewLifecycleOwner, Observer {
//            if(it==1)
//            {Toast.makeText(context, "IMDb Rating", Toast.LENGTH_SHORT).show()}
//
//            itemTvViewModel.logoClickDone()
//        })



        //add to list button

        binding.addToList.setOnClickListener{
            var firebaseDatabase = Firebase.database
            var dbreference = firebaseDatabase.getReference("users")
            val auth = Firebase.auth
            val uid = auth.uid

            val tmdb = id.toString()
            val imdbRating= binding.imdbScore.text.toString()
            val name  = binding.title.text.toString()
            val posterPath = binding.posterPathHelper.text.toString()
            val year = binding.yearHelper.text.subSequence(0,4).toString()


            var tvFirebaseObject = TvFirebase(tmdb,imdbRating,name,posterPath,year)

            dbreference.child("$uid/tv/$tmdb").setValue(tvFirebaseObject)

            Toast.makeText(context,"Added",Toast.LENGTH_SHORT).show()

        }





        return binding.root
    }




}