package com.utkarsh.fxn.ui.searchresult.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SearchTvViewModelFactory(
    private val searchTitle : String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchTvViewModel::class.java)) {
            return SearchTvViewModel(searchTitle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}