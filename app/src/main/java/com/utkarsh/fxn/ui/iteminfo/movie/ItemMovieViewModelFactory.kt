package com.utkarsh.fxn.ui.iteminfo.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utkarsh.fxn.ui.iteminfo.tv.ItemTvViewModel

class ItemMovieViewModelFactory (
    private val itemId: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemMovieViewModel::class.java)) {
            return ItemMovieViewModel(itemId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}