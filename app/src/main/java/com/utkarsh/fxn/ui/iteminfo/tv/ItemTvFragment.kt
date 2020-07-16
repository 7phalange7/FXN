package com.utkarsh.fxn.ui.iteminfo.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.utkarsh.fxn.databinding.FragmentItemTvBinding
import com.utkarsh.fxn.databinding.FragmentIteminfoBinding
import com.utkarsh.fxn.ui.searchresult.tv.SearchTvViewModel

class ItemTvFragment : Fragment() {

    private lateinit var itemTvViewModel: ItemTvViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemTvBinding.inflate(inflater)

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


//        //find why this not working !!!!!!!!
//        itemTvViewModel.iconClicked.observe(viewLifecycleOwner, Observer {
//            if(it==1)
//            {Toast.makeText(context, "IMDb Rating", Toast.LENGTH_SHORT).show()}
//
//            itemTvViewModel.logoClickDone()
//        })







        return binding.root
    }


}