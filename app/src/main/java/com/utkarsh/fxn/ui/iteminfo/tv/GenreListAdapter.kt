package com.utkarsh.fxn.ui.iteminfo.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.fxn.data.Result
import com.utkarsh.fxn.data.tmdbTvbyid.Genre
import com.utkarsh.fxn.databinding.GenreItemChipBinding
import com.utkarsh.fxn.databinding.SearchListItemBinding
import com.utkarsh.fxn.ui.searchresult.SearchListAdapter

class GenreListAdapter : ListAdapter<Genre, GenreListAdapter.GenreViewHolder>(GenreDiffCallback) {
    class GenreViewHolder (private var binding: GenreItemChipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre : Genre) {
            binding.tvinfo = genre
            binding.executePendingBindings()
        }

    }

    object GenreDiffCallback : DiffUtil.ItemCallback<Genre>(){

        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(GenreItemChipBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}