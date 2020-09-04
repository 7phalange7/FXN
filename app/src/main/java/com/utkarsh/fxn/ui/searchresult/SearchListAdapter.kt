package com.utkarsh.fxn.ui.searchresult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.fxn.data.Result
import com.utkarsh.fxn.databinding.SearchListItemBinding

class SearchListAdapter (private val clickListener: MovieClickListener) :
    ListAdapter<Result, SearchListAdapter.ResultViewHolder>(ResultDiffCallback) {
    object ResultDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class ResultViewHolder(private var binding: SearchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(result: Result, clickListener: MovieClickListener) {
            binding.resultObject = result
            binding.clicklistener=clickListener
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(SearchListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }


    class MovieClickListener(val listener: (id: Int) -> Unit) {
        fun onClick(result: Result) = listener(result.id)
    }
}