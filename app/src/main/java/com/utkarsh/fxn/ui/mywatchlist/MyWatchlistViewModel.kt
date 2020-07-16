package com.utkarsh.fxn.ui.mywatchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyWatchlistViewModel : ViewModel() {

    private val _fabClicked = MutableLiveData<Boolean>()
    val fabClicked: LiveData<Boolean>
        get() = _fabClicked

    init {
        _fabClicked.value = false
    }

    fun fabclicked() {
        _fabClicked.value = _fabClicked.value == false
    }

}