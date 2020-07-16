package com.utkarsh.fxn.ui.iteminfo.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utkarsh.fxn.ui.searchresult.tv.SearchTvViewModel

class ItemTvViewModelFactory(
    private val itemId: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemTvViewModel::class.java)) {
            return ItemTvViewModel(itemId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}