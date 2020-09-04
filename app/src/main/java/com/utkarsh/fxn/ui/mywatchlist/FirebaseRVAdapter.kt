package com.utkarsh.fxn.ui.mywatchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.utkarsh.fxn.data.firebase.TvFirebase
import com.utkarsh.fxn.databinding.MywatchlistListItemBinding

class FirebaseRVAdapter(private val clickListener: ItemClickListener , options: FirebaseRecyclerOptions<TvFirebase>)
    : FirebaseRecyclerAdapter<TvFirebase, FirebaseRVAdapter.tvFirebaseViewHolder>(options) {
    class tvFirebaseViewHolder ( private var binding: MywatchlistListItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            tvfirebase: TvFirebase,
            clickListener: ItemClickListener
        ){
            binding.tvFirebase=tvfirebase
            binding.clickListener=clickListener
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tvFirebaseViewHolder {
        return tvFirebaseViewHolder(MywatchlistListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: tvFirebaseViewHolder, position: Int, model: TvFirebase) {
        return holder.bind(model!!,clickListener)
    }

    class ItemClickListener(val listener : (id : Int?) -> Unit){

        fun OnClick(tvfirebase: TvFirebase) = listener(tvfirebase.id?.toInt())
    }


}