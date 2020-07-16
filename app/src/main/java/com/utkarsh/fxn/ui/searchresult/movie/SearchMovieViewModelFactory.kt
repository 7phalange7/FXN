package com.utkarsh.fxn.ui.searchresult.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SearchMovieViewModelFactory(
    private val searchTitle : String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchMovieViewModel::class.java)) {
            return SearchMovieViewModel(searchTitle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}